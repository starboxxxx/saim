package moobean.saim.server.community.clubMember.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record DelegateMasterRequest(
        @Schema(description = "위임할 회원 ID")
        Long targetUserId,
        @Schema(description = "해당 클럽 ID")
        Long clubId
) {
}
