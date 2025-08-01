package moobean.saim.server.oauth.service;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.oauth.controller.port.OAuthService;
import moobean.saim.server.oauth.controller.request.OAuthLoginRequest;
import moobean.saim.server.user.domain.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Primary
@RequiredArgsConstructor
public class UnifiedOAuthService implements OAuthService {

    private final Map<String, OAuthServiceImpl> oauthServices;
    @Override
    public User authenticate(OAuthLoginRequest request) {
        OAuthServiceImpl service = oauthServices.get(request.provider());

        if (service == null) {
            throw new IllegalArgumentException("지원하지 않는 OAuth provider");
        }
        return service.authenticate(request);
    }
}
