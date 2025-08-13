package moobean.saim.server.global.config.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import moobean.saim.server.global.jwt.JwtAuthenticationFilter;
import moobean.saim.server.global.jwt.JwtExceptionFilter;
import moobean.saim.server.global.jwt.JwtProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class SecurityFilterConfig {
    private final UserDetailsService userDetailsService;
    private final AccessDeniedHandler accessDeniedHandler;
    private final JwtProvider accessTokenProvider;

    @Bean
    public JwtExceptionFilter jwtExceptionFilter() {
        return new JwtExceptionFilter();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(userDetailsService, accessDeniedHandler, accessTokenProvider);
    }
}
