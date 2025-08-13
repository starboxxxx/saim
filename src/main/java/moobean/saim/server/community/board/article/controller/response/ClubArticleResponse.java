package moobean.saim.server.community.board.article.controller.response;

import java.util.List;

public record ClubArticleResponse(
        Boolean isSecret,
        List<ArticleListResponse> articles
) {
}
