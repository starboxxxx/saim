package moobean.saim.server.community.board.comment.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateCommentRequest(
        @Schema(description = "댓글 작성할 게시글 ID")
        Long articleId,
        @Schema(description = "댓글 내용")
        String content
) {
}
