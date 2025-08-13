package moobean.saim.server.community.board.comment.domain;

import lombok.Builder;
import lombok.Getter;
import moobean.saim.server.community.board.article.domain.Article;
import moobean.saim.server.community.club.domain.Club;
import moobean.saim.server.user.domain.User;

import java.time.LocalDateTime;

@Getter
public class Comment {

    private Long id;

    private String content;

    private Article article;

    private User commentWriter;

    private LocalDateTime createdTime;

    @Builder
    public Comment(Long id, String content,
                   Article article, User commentWriter,
                   LocalDateTime createdTime) {
        this.id = id;
        this.content = content;
        this.article = article;
        this.commentWriter = commentWriter;
        this.createdTime = createdTime;
    }

    public static Comment create(User commentWriter, Article article,
                                 String content) {
        return Comment.builder()
                .content(content)
                .article(article)
                .commentWriter(commentWriter)
                .build();
    }
}
