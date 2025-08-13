package moobean.saim.server.community.clubMember.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record DenyClubMemberRequest(
        @Schema(description = "승인 거부할 회원 ID")
        Long denyTargetUserId,
        @Schema(description = "해당 클럽 ID")
        Long clubId
) {
}
