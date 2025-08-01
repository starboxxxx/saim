package moobean.saim.server.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import moobean.saim.server.global.exception.JwtException;
import moobean.saim.server.global.exception.code.AuthErrorCode;
import moobean.saim.server.global.jwt.annoation.AccessTokenStrategy;
import moobean.saim.server.global.jwt.annoation.RefreshTokenStrategy;
import moobean.saim.server.global.jwt.claim.AccessTokenClaim;
import moobean.saim.server.global.jwt.claim.JwtClaims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Slf4j
@Component
@RefreshTokenStrategy
public class RefreshTokenProvider implements JwtProvider{

    private final SecretKey secretKey;
    private final Duration tokenExpiration;

    public RefreshTokenProvider(@Value("${jwt.secret.refresh-token}") String jwtSecretKey,
                                @Value("${jwt.secret.refresh-token.expiration-time}") Duration tokenExpiration) {
        byte[] keyBytes = Base64.getDecoder().decode(jwtSecretKey);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        this.tokenExpiration = tokenExpiration;
    }


    @Override
    public String createToken(JwtClaims claims) {
        Date now = new Date();

        return Jwts.builder()
                .setHeader(createHeader())
                .setClaims(claims.getClaims())
                .signWith(secretKey)
                .setExpiration(createExpirationDate(now, tokenExpiration.toMillis()))
                .compact();
    }

    @Override
    public JwtClaims parseJwtClaimsFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return AccessTokenClaim.of(
                Long.parseLong(claims.get("id", String.class))
        );
    }

    @Override
    public LocalDateTime getExpiredDate(String token) {
        Claims claims = getClaimsFromToken(token);
        return Instant.ofEpochMilli(claims.getExpiration().getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
    }

    @Override
    public boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            log.error("Token is Expired: {}", e.getMessage());
            throw new JwtException(AuthErrorCode.EXPIRED_ACCESS_TOKEN);
        }
    }

    @Override
    public Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.warn("Token is invalid: {}", e.getMessage());
            throw new JwtException(AuthErrorCode.INVALID_TOKEN);
        }
    }

    private Map<String, Object> createHeader() {
        return Map.of(
                "typ", "JWT",
                "alg", "HS256",
                "regDate", System.currentTimeMillis()
        );
    }

    private Date createExpirationDate(Date now, long expirationTime) {
        return new Date(now.getTime() + expirationTime);
    }
}
