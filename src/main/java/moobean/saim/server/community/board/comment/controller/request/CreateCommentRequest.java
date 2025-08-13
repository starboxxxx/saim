package moobean.saim.server.community.board.comment.controller.request;

public record CreateCommentRequest(
        Long articleId,
        String content
) {
}
