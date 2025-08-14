package moobean.saim.server.community.board.article.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import moobean.saim.server.community.board.article.domain.Article;
import moobean.saim.server.community.board.comment.infrastructure.entity.CommentEntity;
import moobean.saim.server.community.board.recommend.infrastructure.entity.RecommendEntity;
import moobean.saim.server.community.club.infrastructure.entity.ClubEntity;
import moobean.saim.server.community.follow.infrastructure.entity.FollowEntity;
import moobean.saim.server.global.BaseTimeEntity;
import moobean.saim.server.user.infrastructure.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ARTICLE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Column(nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long recommendCount;

    @Column(nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long commentCount;

    @Column(nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "club_id", nullable = false)
    private ClubEntity club;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity articleWriter;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<CommentEntity> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<RecommendEntity> recommendList = new ArrayList<>();

    public static ArticleEntity from(Article article) {
        ArticleEntity entity = new ArticleEntity();
        entity.id = article.getId();
        entity.title = article.getTitle();
        entity.content = article.getContent();
        entity.recommendCount = article.getRecommendCount();
        entity.commentCount = article.getCommentCount();
        entity.viewCount = article.getViewCount();
        entity.club = ClubEntity.from(article.getClub());
        entity.articleWriter = UserEntity.from(article.getArticleWriter());
        return entity;
    }

    public Article toModel() {
        return Article.builder()
                .id(id)
                .title(title)
                .content(content)
                .recommendCount(recommendCount)
                .commentCount(commentCount)
                .viewCount(viewCount)
                .club(club.toModel())
                .articleWriter(articleWriter.toModel())
                .createdTime(getCreatedTime())
                .build();
    }


}
