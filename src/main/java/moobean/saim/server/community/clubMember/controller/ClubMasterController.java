package moobean.saim.server.community.clubMember.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.clubMember.controller.response.InviteMemberListResponse;
import moobean.saim.server.community.clubMember.controller.response.MasterClubMemberListResponse;
import moobean.saim.server.community.clubMember.controller.response.PendingMemberListResponse;
import moobean.saim.server.community.clubMember.controller.port.*;
import moobean.saim.server.community.clubMember.controller.request.*;
import moobean.saim.server.global.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "05. ClubMaster(클럽장)")
@RestController
@RequiredArgsConstructor
@RequestMapping("/clubMaster")
public class ClubMasterController {

    private final FindClubMasterService findClubMemberService;
    private final InviteClubMemberService inviteClubMemberService;
    private final ApproveClubMemberService approveClubMemberService;
    private final DropClubMemberService dropClubMemberService;
    private final DenyClubMemberService denyClubMemberService;
    private final DelegateMasterService delegateMasterService;

    @Operation(summary = "클럽 수정에서 회원 목록 조회")
    @GetMapping("/memberList/{clubId}")
    public ResponseEntity<List<MasterClubMemberListResponse>> findMemberListByMaster(@AuthenticationPrincipal CustomUserDetails userInfo,
                                                                                     @PathVariable Long clubId) {
        return ResponseEntity.ok(findClubMemberService.findClubMemberListByMaster(userInfo.user().getId(), clubId));
    }

    @Operation(summary = "클럽 가입 대기중인 회원 목록 조회")
    @Parameter(name = "clubId", description = "조회할 클럽 ID", in = ParameterIn.PATH)
    @GetMapping("/pending/{clubId}")
    public ResponseEntity<List<PendingMemberListResponse>> getPendingMemberList(@AuthenticationPrincipal CustomUserDetails userInfo,
                                                                                @PathVariable Long clubId) {

        return ResponseEntity.ok(findClubMemberService.findPendingMemberList(userInfo.user().getId(), clubId));
    }

    @Operation(summary = "클럽에 초대할 회원 목록 조회")
    @Parameter(name = "clubId", description = "조회할 클럽 ID", in = ParameterIn.PATH)
    @GetMapping("/invite/{clubId}")
    public ResponseEntity<List<InviteMemberListResponse>> getInviteMemberList(@AuthenticationPrincipal CustomUserDetails userInfo,
                                                                              @PathVariable Long clubId) {
        return ResponseEntity.ok(findClubMemberService.findInviteMemberList(userInfo.user().getId(), clubId));
    }

    @Operation(summary = "클럽 회원 초대")
    @PostMapping("/invite")
    public ResponseEntity<Void> inviteMember(@AuthenticationPrincipal CustomUserDetails userInfo,
                                             @RequestBody InviteClubMemberRequest request) {

        inviteClubMemberService.inviteMember(userInfo.user().getId(), request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "클럽 가입 신청 승인")
    @PatchMapping("/approve")
    public ResponseEntity<Void> approve(@AuthenticationPrincipal CustomUserDetails userInfo,
                                        @RequestBody ApproveClubMemberRequest request) {
        approveClubMemberService.approveClubMember(userInfo.user().getId(), request);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "클럽장 위임")
    @PatchMapping("/master")
    public ResponseEntity<Void> delegateMaster(@AuthenticationPrincipal CustomUserDetails userInfo,
                                               @RequestBody DelegateMasterRequest request) {
        delegateMasterService.delegateMaster(userInfo.user().getId(), request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "회원 삭제")
    @DeleteMapping
    public ResponseEntity<Void> dropClubMember(@AuthenticationPrincipal CustomUserDetails userInfo,
                                               @RequestBody DropClubMemberRequest request) {
        dropClubMemberService.dropClubMember(userInfo.user().getId(), request);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "클럽 승인 거절")
    @DeleteMapping("/deny")
    public ResponseEntity<Void> denyClubMember(@AuthenticationPrincipal CustomUserDetails userInfo,
                                               @RequestBody DenyClubMemberRequest request) {
        denyClubMemberService.denyClubMember(userInfo.user().getId(), request);

        return ResponseEntity.noContent().build();
    }
}
