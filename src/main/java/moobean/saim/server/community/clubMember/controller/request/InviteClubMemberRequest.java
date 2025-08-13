package moobean.saim.server.community.clubMember.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record InviteClubMemberRequest(
        @Schema(description ="초대할 회원 ID")
        Long targetUserId,
        @Schema(description = "회원을 초대할 클럽 ID")
        Long clubId
) {
}
