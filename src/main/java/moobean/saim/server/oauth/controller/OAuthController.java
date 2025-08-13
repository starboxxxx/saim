package moobean.saim.server.oauth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import moobean.saim.server.global.security.CustomUserDetails;
import moobean.saim.server.oauth.controller.port.OAuthService;
import moobean.saim.server.oauth.controller.port.TokenService;
import moobean.saim.server.oauth.controller.request.OAuthLoginRequest;
import moobean.saim.server.oauth.controller.response.TokenResponse;
import moobean.saim.server.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "01. OAuth")
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthService oAuthService;
    private final TokenService tokenService;

    @Operation(summary = "소셜 로그인 및 회원가입")
    @Parameters({
            @Parameter(name = "provider", description = "소셜 로그인 플랫폼", required = true, in = ParameterIn.PATH),
            @Parameter(name = "code", description = "소셜 로그인 인증 코드", required = true, in = ParameterIn.QUERY),
    })
    @PostMapping("/{provider}/login")
    public ResponseEntity<TokenResponse> login(@PathVariable String provider,
                                               @RequestParam("code") String code) {
        OAuthLoginRequest oAuthLoginRequest = new OAuthLoginRequest(provider, code);
        User user = oAuthService.authenticate(oAuthLoginRequest);
        return ResponseEntity.ok(tokenService.issueTokens(user.getId()));
    }

    @Operation(summary = "토큰 재발급")
    @Parameter(name = "refreshToken", description = "재발급 토큰", required = true, in = ParameterIn.HEADER)
    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> reissue(@RequestHeader(value = "refreshToken") String refreshToken) {
        return ResponseEntity.ok(tokenService.reissueToken(refreshToken));
    }

    @Operation(summary = "로그아웃")
    @Parameter(name = "refreshToken", description = "로그아웃 요청 회원의 재발급 토큰", required = true, in = ParameterIn.HEADER)
    @PostMapping("/logout")
    public void logout(@AuthenticationPrincipal CustomUserDetails userInfo,
                       @RequestHeader(value= "refreshToken") String refreshToken) {
        tokenService.logout(userInfo.user().getId(), refreshToken);
    }
}
