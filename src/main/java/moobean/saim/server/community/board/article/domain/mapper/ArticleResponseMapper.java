package moobean.saim.server.community.board.article.domain.mapper;

import moobean.saim.server.community.board.article.controller.response.ArticleDetailResponse;
import moobean.saim.server.community.board.article.controller.response.ArticleListResponse;
import moobean.saim.server.community.board.article.controller.response.ArticleResponse;
import moobean.saim.server.community.board.article.controller.response.ClubArticleResponse;
import moobean.saim.server.community.board.article.domain.Article;
import moobean.saim.server.community.board.comment.controller.response.CommentListResponse;
import moobean.saim.server.user.domain.User;

import java.util.List;

public class ArticleResponseMapper {

    public static ArticleDetailResponse toArticleDetailResponse(ArticleResponse article,
                                                                List<CommentListResponse> commentList) {
        return new ArticleDetailResponse(
                article,
                commentList
        );
    }

    public static ArticleResponse toArticleResponse(User articleWriter, Article article) {
        return new ArticleResponse(
                articleWriter.getId(),
                articleWriter.getName(),
                article.getClub().getName(),
                article.getContent(),
                article.getRecommendCount(),
                article.getCommentCount(),
                article.getRecommendCount(),
                article.getCreatedTime()
        );
    }

    public static ClubArticleResponse toClubArticleResponse(Boolean isSecret, List<ArticleListResponse> articles) {
        return new ClubArticleResponse(isSecret, articles);
    }

    public static ArticleListResponse toArticleListResponse(User articleWriter, Article article) {
        return new ArticleListResponse(
                articleWriter.getId(),
                articleWriter.getName(),
                article.getClub().getName(),
                article.getContent(),
                article.getRecommendCount(),
                article.getCommentCount(),
                article.getCreatedTime()
        );
    }
}
