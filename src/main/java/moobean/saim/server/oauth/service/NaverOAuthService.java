package moobean.saim.server.oauth.service;

import moobean.saim.server.oauth.domain.OAuthUserInfo;
import moobean.saim.server.oauth.controller.request.OAuthLoginRequest;
import moobean.saim.server.oauth.domain.NaverUserInfo;
import moobean.saim.server.user.service.port.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Component("naver")
public class NaverOAuthService extends OAuthServiceImpl {

    @Value("${oauth.naver.client-id}")
    private String clientId;
    @Value("${oauth.naver.client-secret}")
    private String clientSecret;
    @Value("${oauth.naver.redirect-uri}")
    private String redirectUri;
    @Value("${oauth.naver.token-uri}")
    private String tokenUrl;
    @Value("${oauth.naver.user-info-uri}")
    private String userInfoUrl;

    public NaverOAuthService(UserRepository userRepository) {
        super(userRepository);
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
        return new NaverUserInfo(attributes);
    }
}
