package moobean.saim.server.oauth.service;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.global.jwt.JwtProvider;
import moobean.saim.server.global.jwt.claim.AccessTokenClaim;
import moobean.saim.server.global.jwt.claim.JwtClaims;
import moobean.saim.server.global.jwt.claim.RefreshTokenClaim;
import moobean.saim.server.oauth.controller.port.TokenService;
import moobean.saim.server.oauth.controller.response.TokenResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final JwtProvider accessTokenProvider;
    private final JwtProvider refreshTokenProvider;
    private final RefreshTokenService refreshTokenService;

    @Override
    public TokenResponse issueTokens(Long userId) {
        String accessToken = accessTokenProvider.createToken(AccessTokenClaim.of(userId));
        String refreshToken = refreshTokenProvider.createToken(RefreshTokenClaim.of(userId));

        refreshTokenService.saveRefreshToken(userId, refreshToken);
        return new TokenResponse(accessToken, refreshToken);
    }

    @Override
    public TokenResponse reissueToken(String oldRefreshToken) {
        Long userId = resolveRefreshToken(oldRefreshToken);

        refreshTokenService.validateRefreshToken(userId, oldRefreshToken);

        return issueTokens(userId);
    }

    @Override
    public void logout(Long userId, String refreshToken) {

        refreshTokenService.validateRefreshToken(userId, refreshToken);
        refreshTokenService.removeRefreshToken(userId);
    }

    private Long resolveRefreshToken(String refreshToken) {
        JwtClaims claims = refreshTokenProvider.parseJwtClaimsFromToken(refreshToken);
        return getClaimValue(claims, "id", Long::parseLong);
    }

    private static <T> T getClaimValue(JwtClaims claims, String key, Function<String, T> converter) {
        Object value = claims.getClaims().get(key);
        if (value != null) {
            return converter.apply((String) value);
        }
        return null;
    }
}
