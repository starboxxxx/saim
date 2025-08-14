package moobean.saim.server.community.board.recommend.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import moobean.saim.server.community.board.article.infrastructure.entity.ArticleEntity;
import moobean.saim.server.community.board.comment.domain.Comment;
import moobean.saim.server.community.board.comment.infrastructure.entity.CommentEntity;
import moobean.saim.server.community.board.recommend.domain.Recommend;
import moobean.saim.server.global.BaseTimeEntity;
import moobean.saim.server.user.infrastructure.entity.UserEntity;

@Entity
@Table(name = "RECOMMEND")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private ArticleEntity article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private CommentEntity comment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity recommender;

    public static RecommendEntity from(Recommend recommend) {
        RecommendEntity entity = new RecommendEntity();
        entity.id = recommend.getId();
        entity.article = ArticleEntity.from(recommend.getArticle());
        entity.comment = CommentEntity.from(recommend.getComment());
        entity.recommender = UserEntity.from(recommend.getRecommender());
        return entity;
    }

    public Recommend toModel() {
        return Recommend.builder()
                .id(id)
                .article(article.toModel())
                .comment(comment.toModel())
                .recommender(recommender.toModel())
                .build();
    }
}
