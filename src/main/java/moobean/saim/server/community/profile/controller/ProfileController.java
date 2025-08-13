package moobean.saim.server.community.profile.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.profile.controller.port.FindProfileService;
import moobean.saim.server.community.profile.controller.port.UpdateProfileService;
import moobean.saim.server.community.profile.controller.request.UpdateProfileRequest;
import moobean.saim.server.community.profile.controller.response.MyPageProfileResponse;
import moobean.saim.server.community.profile.controller.response.ProfileResponse;
import moobean.saim.server.community.profile.controller.response.TrainerResponse;
import moobean.saim.server.global.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "03. Profile")
@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final FindProfileService findProfileService;
    private final UpdateProfileService updateProfileService;

    @Operation(summary = "트레이너 수정 페이지에서 기존 트레이너 조회")
    @GetMapping("/trainer")
    public ResponseEntity<TrainerResponse> getTrainer(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity.ok(findProfileService.getTrainer(customUserDetails.user().getId()));
    }

    @Operation(summary = "회원 프로필 조회")
    @Parameter(name = "userId", description = "프로필 조회할 회원 ID", in = ParameterIn.PATH)
    @GetMapping("/{userId}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable Long userId,
                                      @AuthenticationPrincipal CustomUserDetails userInfo) {
        return ResponseEntity.ok(findProfileService.getProfile(userId, userInfo.user().getId()));
    }

    @Operation(summary = "마이페이지에서 프로필 조회")
    @GetMapping("/mypage")
    public ResponseEntity<MyPageProfileResponse> getMyPageProfile(@AuthenticationPrincipal CustomUserDetails userInfo) {
        return ResponseEntity.ok(findProfileService.getMyPageProfile(userInfo.user().getId()));
    }

    @Operation(summary = "프로필 수정")
    @PatchMapping("/mypage")
    public ResponseEntity<Void> updateMyPageProfile(@AuthenticationPrincipal CustomUserDetails userInfo,
                                                    @RequestBody UpdateProfileRequest request) {
        updateProfileService.updateProfile(userInfo.user().getId(), request);
        return ResponseEntity.noContent().build();
    }
}
