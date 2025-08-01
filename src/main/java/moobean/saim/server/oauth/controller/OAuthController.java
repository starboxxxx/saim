package moobean.saim.server.oauth.controller;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.global.security.CustomUserDetails;
import moobean.saim.server.oauth.controller.port.OAuthService;
import moobean.saim.server.oauth.controller.port.TokenService;
import moobean.saim.server.oauth.controller.request.OAuthLoginRequest;
import moobean.saim.server.oauth.controller.request.RefreshTokenRequest;
import moobean.saim.server.oauth.controller.response.TokenResponse;
import moobean.saim.server.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthService oAuthService;
    private final TokenService tokenService;

    @PostMapping("/{provider}/login")
    public ResponseEntity<TokenResponse> login(@PathVariable String provider,
                                               @RequestParam("code") String code) {
        OAuthLoginRequest oAuthLoginRequest = new OAuthLoginRequest(provider, code);
        User user = oAuthService.authenticate(oAuthLoginRequest);
        return ResponseEntity.ok(tokenService.issueTokens(user.getId()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> reissue(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(tokenService.reissueToken(request.refreshToken()));
    }

    @PostMapping("/logout")
    public void logout(@AuthenticationPrincipal CustomUserDetails userInfo,
                       @RequestHeader(value= "refreshToken") String refreshToken) {
        tokenService.logout(userInfo.user().getId(), refreshToken);
    }
}
