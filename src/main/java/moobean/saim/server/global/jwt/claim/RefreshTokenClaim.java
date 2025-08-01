package moobean.saim.server.global.jwt.claim;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshTokenClaim implements JwtClaims{

    private final Map<String, Object> claims;

    public static RefreshTokenClaim of(Long memberId) {
        Map<String, Object> claims = Map.of(
                "id", memberId.toString()
        );
        return new RefreshTokenClaim(claims);
    }

    @Override
    public Map<String, Object> getClaims() {
        return claims;
    }
}
