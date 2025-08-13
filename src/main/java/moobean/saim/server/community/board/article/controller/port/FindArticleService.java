package moobean.saim.server.community.board.article.controller.port;

import moobean.saim.server.community.board.article.controller.response.ArticleDetailResponse;
import moobean.saim.server.community.board.article.controller.response.ArticleListResponse;
import moobean.saim.server.community.board.article.controller.response.ClubArticleResponse;

import java.util.List;

public interface FindArticleService {

    ArticleDetailResponse findArticle(Long userId, Long articleId);

    ClubArticleResponse findClubArticle(Long userId, Long clubId);
}
