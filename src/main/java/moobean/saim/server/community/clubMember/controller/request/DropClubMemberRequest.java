package moobean.saim.server.community.clubMember.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record DropClubMemberRequest(
        @Schema(description = "삭제할 회원 ID")
        Long userId,
        @Schema(description = "해당 클럽 ID")
        Long clubId
) {
}
