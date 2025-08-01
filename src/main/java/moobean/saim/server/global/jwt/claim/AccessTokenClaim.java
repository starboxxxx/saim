package moobean.saim.server.global.jwt.claim;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AccessTokenClaim implements JwtClaims{

    private final Map<String, Object> claims;

    public static AccessTokenClaim of(Long userId) {
        Map<String, Object> claims = Map.of(
                "id", userId.toString()
        );
        return new AccessTokenClaim(claims);
    }

    @Override
    public Map<String, Object> getClaims() {
        return claims;
    }
}
