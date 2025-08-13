package moobean.saim.server.community.club.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateClubRequest(
        @Schema(description = "수정된 클럽 이름")
        String name,
        @Schema(description = "수정된 클럽 소개글")
        String introduce,
        @Schema(description = "수정된 클럽 공개 여부")
        Boolean showClub
) {
}
