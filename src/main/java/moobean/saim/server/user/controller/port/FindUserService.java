package moobean.saim.server.user.controller.port;

import moobean.saim.server.oauth.domain.OAuthUserInfo;
import moobean.saim.server.user.domain.User;

public interface FindUserService {

    User findBySocialId(OAuthUserInfo userInfo);
}
