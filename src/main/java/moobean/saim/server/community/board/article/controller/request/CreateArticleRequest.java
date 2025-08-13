package moobean.saim.server.community.board.article.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateArticleRequest(
        @Schema(description = "게시글 작성할 클럽 ID")
        Long clubId,
        @Schema(description = "게시글 제목")
        String title,
        @Schema(description = "게시글 내용")
        String content
) {
}
