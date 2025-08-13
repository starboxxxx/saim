package moobean.saim.server.oauth.service;

import moobean.saim.server.oauth.domain.OAuthUserInfo;
import moobean.saim.server.oauth.controller.request.OAuthLoginRequest;
import moobean.saim.server.oauth.domain.GoogleUserInfo;
import moobean.saim.server.user.controller.port.FindUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Component("google")
public class GoogleOAuthService extends OAuthServiceImpl {

    @Value("${oauth.google.client-id}")
    private String clientId;
    @Value("${oauth.google.client-secret}")
    private String clientSecret;
    @Value("${oauth.google.redirect-uri}")
    private String redirectUri;
    @Value("${oauth.google.token-uri}")
    private String tokenUrl;
    @Value("${oauth.google.user-info-uri}")
    private String userInfoUrl;

    public GoogleOAuthService(FindUserService userService) {
        super(userService);
    }

    @Override
    protected MultiValueMap<String, String> createParams(OAuthLoginRequest request) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("code", request.code());
        return params;
    }

    @Override
    protected String getTokenUrl() {
        return tokenUrl;
    }

    @Override
    protected String getUserInfoUrl() {
        return userInfoUrl;
    }

    @Override
    protected OAuthUserInfo createUserInfo(Map<String, Object> attributes) {
        return new GoogleUserInfo(attributes);
    }
}
