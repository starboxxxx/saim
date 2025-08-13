package moobean.saim.server.community.profile.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateProfileRequest(
        @Schema(description = "수정된 회원 이름")
        String name,

        @Schema(description = "수정된 회원 이메일")
        String email,

        @Schema(description = "수정된 소개글")
        String introduce
) {
}
