package moobean.saim.server.oauth.controller.port;

import moobean.saim.server.oauth.controller.request.OAuthLoginRequest;
import moobean.saim.server.user.domain.User;

public interface OAuthService {

    User authenticate(OAuthLoginRequest request);
}
