package moobean.saim.server.community.board.recommend.domain;

import lombok.Builder;
import lombok.Getter;
import moobean.saim.server.community.board.article.domain.Article;
import moobean.saim.server.community.board.comment.domain.Comment;
import moobean.saim.server.user.domain.User;

@Getter
public class Recommend {

    private Long id;
    private Article article;
    private Comment comment;
    private User recommender;

    @Builder
    public Recommend(Long id, Article article, Comment comment,
                     User recommender) {
        this.id = id;
        this.article = article;
        this.comment = comment;
        this.recommender = recommender;
    }

    public static Recommend createArticleRecommend(Article article, User recommender) {
        return Recommend.builder()
                .article(article)
                .recommender(recommender)
                .build();
    }

    public static Recommend createCommentRecommend(Comment comment, User recommender) {
        return Recommend.builder()
                .comment(comment)
                .recommender(recommender)
                .build();
    }
}
