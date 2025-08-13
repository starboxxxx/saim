package moobean.saim.server.oauth.service;

import moobean.saim.server.oauth.domain.OAuthUserInfo;
import moobean.saim.server.oauth.controller.request.OAuthLoginRequest;
import moobean.saim.server.oauth.domain.KakaoUserInfo;
import moobean.saim.server.user.controller.port.FindUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@Component("kakao")
public class KakaoOAuthService extends OAuthServiceImpl {

    @Value("${oauth.kakao.client-id}")
    private String clientId;
    @Value("${oauth.kakao.client-secret}")
    private String clientSecret;
    @Value("${oauth.kakao.redirect-uri}")
    private String redirectUri;
    @Value("${oauth.kakao.token-uri}")
    private String tokenUrl;
    @Value("${oauth.kakao.user-info-uri}")
    private String userInfoUrl;

    public KakaoOAuthService(FindUserService userService) {
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
        return new KakaoUserInfo(attributes);
    }
}
