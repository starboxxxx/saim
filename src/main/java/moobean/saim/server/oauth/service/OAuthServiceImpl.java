package moobean.saim.server.oauth.service;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.domain.Profile;
import moobean.saim.server.community.service.port.ProfileRepository;
import moobean.saim.server.oauth.controller.port.OAuthService;
import moobean.saim.server.oauth.domain.OAuthUserInfo;
import moobean.saim.server.oauth.controller.request.OAuthLoginRequest;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.infrastructure.entity.UserRole;
import moobean.saim.server.user.service.port.UserRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public abstract class OAuthServiceImpl implements OAuthService {

    protected final RestTemplate restTemplate = new RestTemplate();
    protected final UserRepository userRepository;

    @Override
    public User authenticate(OAuthLoginRequest request) {
        MultiValueMap<String, String> params = createParams(request);
        String accessToken = getAccessToken(params);
        Map<String, Object> attributes = getUserAttributes(accessToken);
        OAuthUserInfo userInfo = createUserInfo(attributes);

        return userRepository.findBySocialId(userInfo.getId())
                .orElseGet(() -> userRepository.save(User.create(userInfo)));
    }

    protected String getAccessToken(MultiValueMap<String, String> params) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(getTokenUrl(), requestEntity, Map.class);
        return response.getBody().get("access_token").toString();
    }

    protected Map<String, Object> getUserAttributes(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(getUserInfoUrl(), HttpMethod.GET, entity, Map.class);
        return response.getBody();
    }

    protected abstract MultiValueMap<String, String> createParams(OAuthLoginRequest request);
    protected abstract String getTokenUrl();
    protected abstract String getUserInfoUrl();
    protected abstract OAuthUserInfo createUserInfo(Map<String, Object> attributes);
}
