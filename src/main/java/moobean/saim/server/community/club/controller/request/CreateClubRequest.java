package moobean.saim.server.community.club.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateClubRequest(

        @Schema(description = "생성할 클럽 이름")
        String name,
        @Schema(description = "생성할 클럽 소개글")
        String introduce,
        @Schema(description = "생성할 클럽 공개여부")
        Boolean showClub
) {
}
