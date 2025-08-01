package moobean.saim.server.oauth.controller.port;

import moobean.saim.server.oauth.controller.response.TokenResponse;

public interface TokenService {

    TokenResponse issueTokens(Long userId);

    TokenResponse reissueToken(String refreshToken);

    void logout(Long userId, String refreshToken);
}
