package moobean.saim.server.community.board.article.controller.response;

import moobean.saim.server.community.board.comment.controller.response.CommentListResponse;

import java.util.List;

public record ArticleDetailResponse(
        ArticleResponse article,
        List<CommentListResponse> commentList
) {
}
