package moobean.saim.server.community.controller;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.controller.port.ProfileService;
import moobean.saim.server.community.controller.request.UpdateProfileRequest;
import moobean.saim.server.community.controller.response.MyPageProfileResponse;
import moobean.saim.server.community.controller.response.ProfileResponse;
import moobean.saim.server.global.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/{userId}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable Long userId,
                                      @AuthenticationPrincipal CustomUserDetails userInfo) {
        return ResponseEntity.ok(profileService.getProfile(userId, userInfo.user().getId()));
    }

    @GetMapping("/mypage")
    public ResponseEntity<MyPageProfileResponse> getMyPageProfile(@AuthenticationPrincipal CustomUserDetails userInfo) {
        return ResponseEntity.ok(profileService.getMyPageProfile(userInfo.user().getId()));
    }

    @PatchMapping("/mypage")
    public void updateMyPageProfile(@AuthenticationPrincipal CustomUserDetails userInfo,
                                                    @RequestBody UpdateProfileRequest request) {
        profileService.updateProfile(userInfo.user().getId(), request);
    }
}
