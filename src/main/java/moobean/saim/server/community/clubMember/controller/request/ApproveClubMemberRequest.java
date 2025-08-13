package moobean.saim.server.community.clubMember.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record ApproveClubMemberRequest(
        @Schema(description = "클럽 가입 신청한 회원 ID")
        Long approveTargetUserId,
        @Schema(description = "가입할 클럽 ID")
        Long clubId
) {
}
