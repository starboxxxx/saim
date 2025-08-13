package moobean.saim.server.oauth.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Repository
public class RefreshTokenRepository implements moobean.saim.server.oauth.service.port.RefreshTokenRepository {

    private final RedisTemplate<String, String> redisTemplate;
    private static final long REFRESH_TOKEN_EXPIRE_SECONDS = 60 * 60 * 24 * 7;

    @Override
    public void save(String userId, String refreshToken) {
        redisTemplate.opsForValue().set(getKey(userId), refreshToken,
                REFRESH_TOKEN_EXPIRE_SECONDS, TimeUnit.SECONDS);
    }

    @Override
    public String find(String userId) {
        return redisTemplate.opsForValue().get(getKey(userId));
    }

    @Override
    public void delete(String userId) {
        redisTemplate.delete(getKey(userId));
    }

    private String getKey(String userId) {
        return "refresh_token:" + userId;
    }
}
