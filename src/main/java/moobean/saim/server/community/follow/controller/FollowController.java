package moobean.saim.server.community.follow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.follow.controller.port.FindFollowService;
import moobean.saim.server.community.follow.controller.port.UpdateFollowService;
import moobean.saim.server.community.follow.controller.response.FollowListResponse;
import moobean.saim.server.global.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "07. Follow")
@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {

    private final FindFollowService findFollowService;
    private final UpdateFollowService updateFollowService;

    @Operation(summary = "팔로워 목록 조회")
    @GetMapping("/followers")
    public ResponseEntity<List<FollowListResponse>> getFollowers(
            @AuthenticationPrincipal CustomUserDetails userInfo) {
        return ResponseEntity.ok(findFollowService.getFollowers(userInfo.user().getId()));
    }

    @Operation(summary = "팔로잉 목록 조회")
    @GetMapping("/followings")
    public ResponseEntity<List<FollowListResponse>> getFollowings(
            @AuthenticationPrincipal CustomUserDetails userInfo) {
        return ResponseEntity.ok(findFollowService.getFollowings(userInfo.user().getId()));
    }

    @Operation(summary = "팔로우 신청")
    @Parameter(name = "userId", description = "팔로우 신청할 회원 ID", in = ParameterIn.PATH)
    @PostMapping("/{userId}")
    public ResponseEntity<Void> follow(@AuthenticationPrincipal CustomUserDetails userInfo,
                       @PathVariable Long userId) {
        updateFollowService.doFollow(userInfo.user().getId(), userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "팔로워 삭제")
    @Parameter(name = "userId", description = "삭제할 팔로워 회원 ID", in = ParameterIn.PATH)
    @DeleteMapping("/follower/{userId}")
    public ResponseEntity<Void> dropFollower(@AuthenticationPrincipal CustomUserDetails userInfo,
                       @PathVariable Long userId) {
        updateFollowService.dropFollower(userInfo.user().getId(), userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "팔로잉 삭제")
    @Parameter(name = "userId", description = "삭제할 팔로잉 회원 ID", in = ParameterIn.PATH)
    @DeleteMapping("/following/{userId}")
    public ResponseEntity<Void> dropFollowing(@AuthenticationPrincipal CustomUserDetails userInfo,
                                              @PathVariable Long userId) {
        updateFollowService.dropFollowing(userInfo.user().getId(), userId);
        return ResponseEntity.noContent().build();
    }
}
