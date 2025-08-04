package moobean.saim.server.community.controller;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.controller.port.FollowService;
import moobean.saim.server.community.controller.request.FollowRequest;
import moobean.saim.server.community.controller.response.FollowListResponse;
import moobean.saim.server.global.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;

    @GetMapping("/follower")
    public ResponseEntity<List<FollowListResponse>> getFollowers(
            @AuthenticationPrincipal CustomUserDetails userInfo) {
        return ResponseEntity.ok(followService.getFollowers(userInfo.user().getId()));
    }

    @GetMapping("/following")
    public ResponseEntity<List<FollowListResponse>> getFollowings(
            @AuthenticationPrincipal CustomUserDetails userInfo) {
        return ResponseEntity.ok(followService.getFollowings(userInfo.user().getId()));
    }

    @PostMapping
    public void follow(@AuthenticationPrincipal CustomUserDetails userInfo,
                       @RequestBody FollowRequest followRequest) {
        followService.doFollow(userInfo.user().getId(), followRequest.targetUserId());
    }

    @DeleteMapping
    public void unfollow(@AuthenticationPrincipal CustomUserDetails userInfo,
                       @RequestBody FollowRequest followRequest) {
        followService.unfollow(userInfo.user().getId(), followRequest.targetUserId());
    }
}
