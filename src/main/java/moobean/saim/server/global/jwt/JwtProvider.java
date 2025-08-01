package moobean.saim.server.global.jwt;

import io.jsonwebtoken.Claims;
import moobean.saim.server.global.jwt.claim.JwtClaims;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

public interface JwtProvider {
    default String resolveToken(String header) {
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return "";
    }

    String createToken(JwtClaims claims);

    JwtClaims parseJwtClaimsFromToken(String token);

    LocalDateTime getExpiredDate(String token);

    boolean isTokenExpired(String token);

    Claims getClaimsFromToken(String token);
}
