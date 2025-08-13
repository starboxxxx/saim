package moobean.saim.server.community.clubMember.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.clubMember.controller.response.ClubMemberListResponse;
import moobean.saim.server.community.clubMember.controller.port.CreateClubMemberService;
import moobean.saim.server.community.clubMember.controller.port.FindClubMemberService;
import moobean.saim.server.community.clubMember.controller.port.LeaveClubMemberService;
import moobean.saim.server.global.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "06. ClubMember(일반 회원)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/clubMember")
public class ClubMemberController {

    private final FindClubMemberService findClubMemberService;
    private final CreateClubMemberService createClubMemberService;
    private final LeaveClubMemberService leaveClubMemberService;

    @Operation(summary = "회원 목록 조회")
    @Parameter(name = "clubId", description = "조회할 클럽 ID", in = ParameterIn.PATH)
    @GetMapping("/{clubId}")
    public ResponseEntity<ClubMemberListResponse> getMemberList(@AuthenticationPrincipal CustomUserDetails userInfo,
                                                                @PathVariable Long clubId) {
        return ResponseEntity.ok(findClubMemberService.findClubMembers(userInfo.user().getId(), clubId));
    }

    @Operation(summary = "클럽 가입 신청")
    @Parameter(name = "clubId", description = "가입 신청할 클럽 ID", in = ParameterIn.PATH)
    @PostMapping("/{clubId}")
    public ResponseEntity<Void> create(@AuthenticationPrincipal CustomUserDetails userInfo,
                                       @PathVariable Long clubId) {
        createClubMemberService.create(userInfo.user().getId(), clubId);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "클럽 탈퇴")
    @Parameter(name = "cludId", description = "탈퇴할 클럽 ID", in = ParameterIn.PATH)
    @DeleteMapping("/{clubId}")
    public ResponseEntity<Void> leaveClubMember(@AuthenticationPrincipal CustomUserDetails userInfo,
                                         @PathVariable Long clubId) {
        leaveClubMemberService.leaveClubMember(userInfo.user().getId(), clubId);
        return ResponseEntity.noContent().build();
    }
}
