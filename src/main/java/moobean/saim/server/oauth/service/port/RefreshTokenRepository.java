package moobean.saim.server.oauth.service.port;

public interface RefreshTokenRepository {

    void save(String userId, String refreshToken);
    String find(String userId);
    void delete(String userId);
}
