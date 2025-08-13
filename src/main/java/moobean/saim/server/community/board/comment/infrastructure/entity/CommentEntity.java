package moobean.saim.server.community.board.comment.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import moobean.saim.server.community.board.article.infrastructure.entity.ArticleEntity;
import moobean.saim.server.community.board.comment.domain.Comment;
import moobean.saim.server.global.BaseTimeEntity;
import moobean.saim.server.user.infrastructure.entity.UserEntity;

@Entity
@Table(name = "COMMENT")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private ArticleEntity article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity commentWriter;

    public static CommentEntity from(Comment comment) {
        CommentEntity entity = new CommentEntity();
        entity.id = comment.getId();
        entity.content = comment.getContent();
        entity.article = ArticleEntity.from(comment.getArticle());
        entity.commentWriter = UserEntity.from(comment.getCommentWriter());
        return entity;
    }

    public Comment toModel() {
        return Comment.builder()
                .id(id)
                .content(content)
                .article(article.toModel())
                .commentWriter(commentWriter.toModel())
                .createdTime(getCreatedTime())
                .build();
    }
}
