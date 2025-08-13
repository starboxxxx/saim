package moobean.saim.server.community.club.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.club.controller.port.CreateClubService;
import moobean.saim.server.community.club.controller.port.FindClubService;
import moobean.saim.server.community.club.controller.port.UpdateClubService;
import moobean.saim.server.community.club.controller.request.CreateClubRequest;
import moobean.saim.server.community.club.controller.request.UpdateClubRequest;
import moobean.saim.server.community.club.controller.response.ClubDetailResponse;
import moobean.saim.server.community.club.controller.response.MyClubListResponse;
import moobean.saim.server.community.club.controller.response.MyClubNameListResponse;
import moobean.saim.server.global.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "04. Club")
@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final FindClubService findClubService;
    private final CreateClubService createClubService;
    private final UpdateClubService updateClubService;

    @Operation(summary = "클럽 상세 조회")
    @Parameter(name = "clubId", description = "상세 조회할 클럽 ID", in = ParameterIn.PATH)
    @GetMapping("/{clubId}")
    public ResponseEntity<ClubDetailResponse> getClubDetail(@AuthenticationPrincipal CustomUserDetails userInfo,
                                                            @PathVariable Long clubId) {
        return ResponseEntity.ok(findClubService.getClubDetail(userInfo.user().getId(), clubId));
    }

    @Operation(summary = "회원이 속해 있는 클럽 목록 조회")
    @GetMapping("/myClubList")
    public ResponseEntity<MyClubListResponse> getMyClubList(@AuthenticationPrincipal CustomUserDetails userInfo) {
        return ResponseEntity.ok(findClubService.getMyClubList(userInfo.user().getId()));
    }

    @Operation(summary = "게시글 작성시 클럽 목록 조회")
    @GetMapping("/myPage/myClubList")
    public ResponseEntity<List<MyClubNameListResponse>> getMyClubListName(@AuthenticationPrincipal CustomUserDetails userInfo) {
        return ResponseEntity.ok(findClubService.getMyClubNameList(userInfo.user().getId()));
    }

    @Operation(summary = "클럽 생성")
    @PostMapping
    public ResponseEntity<Void> create(@AuthenticationPrincipal CustomUserDetails userInfo,
                                       @RequestBody CreateClubRequest request) {
        createClubService.createClub(userInfo.user().getId(), request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "클럽 수정")
    @Parameter(name = "clubId", description = "수정할 클럽 ID", in = ParameterIn.PATH)
    @PatchMapping("/{clubId}")
    public ResponseEntity<Void> update(@AuthenticationPrincipal CustomUserDetails userInfo,
                                       @PathVariable Long clubId,
                                       @RequestBody UpdateClubRequest request) {
        updateClubService.updateClub(userInfo.user().getId(), clubId, request);
        return ResponseEntity.noContent().build();
    }
}
