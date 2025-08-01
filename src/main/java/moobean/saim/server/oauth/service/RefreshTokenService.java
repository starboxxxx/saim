package moobean.saim.server.oauth.service;

import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moobean.saim.server.global.exception.JwtException;
import moobean.saim.server.global.exception.code.AuthErrorCode;
import moobean.saim.server.oauth.service.port.RefreshTokenRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public String getRefreshToken(Long userId) {
        String refreshToken = refreshTokenRepository.find(String.valueOf(userId));
        if (refreshToken == null) {
            throw new JwtException(AuthErrorCode.REFRESH_TOKEN_NOT_FOUND);
        }
        return refreshToken;
    }

    public void saveRefreshToken(Long userId, String refreshToken) {
        refreshTokenRepository.save(String.valueOf(userId), refreshToken);
    }

    public void removeRefreshToken(Long userId) {
        refreshTokenRepository.delete(String.valueOf(userId));
    }

    public void validateRefreshToken(Long userId, String expectedRefreshToken) {
        String refreshToken = refreshTokenRepository.find(String.valueOf(userId));
        if (!refreshToken.equals(expectedRefreshToken)) {
            throw new JwtException(AuthErrorCode.REFRESH_TOKEN_MISMATCH);
        };
    }
}
