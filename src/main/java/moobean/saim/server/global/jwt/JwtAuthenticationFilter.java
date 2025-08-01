package moobean.saim.server.global.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moobean.saim.server.global.jwt.claim.JwtClaims;
import moobean.saim.server.oauth.service.RefreshTokenService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static moobean.saim.server.global.security.WebSecurityUrl.HEALTH_CHECK_ENDPOINT;
import static moobean.saim.server.global.security.WebSecurityUrl.READ_ONLY_PUBLIC_ENDPOINTS;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final AccessDeniedHandler accessDeniedHandler;
    private final JwtProvider accessTokenProvider;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();

        return path.startsWith("/oauth/") || path.equals("/favicon.ico");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            String accessToken = resolveAccessToken(request);

            UserDetails userDetails = getUserDetails(accessToken);
            authenticationUser(userDetails, request);
        } catch (AccessDeniedException e) {
            accessDeniedHandler.handle(request, response, e);
        }
        filterChain.doFilter(request, response);
    }

    private String resolveAccessToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String accessToken = accessTokenProvider.resolveToken(authHeader);

        if (!StringUtils.hasText(accessToken)) {
            log.error("access token is empty");
        }

        if (accessTokenProvider.isTokenExpired(accessToken)) {
            log.error("access token is expired");
        }

        return accessToken;
    }

    private UserDetails getUserDetails(String accessToken) {
        JwtClaims claims = accessTokenProvider.parseJwtClaimsFromToken(accessToken);
        String userId = (String)claims.getClaims().get("id");

        return userDetailsService.loadUserByUsername(userId);
    }

    private void authenticationUser(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails,
                        null, userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
