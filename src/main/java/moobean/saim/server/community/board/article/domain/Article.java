package moobean.saim.server.community.board.article.domain;

import lombok.Builder;
import lombok.Getter;
import moobean.saim.server.community.board.article.controller.request.CreateArticleRequest;
import moobean.saim.server.community.club.domain.Club;
import moobean.saim.server.user.domain.User;

import java.time.LocalDateTime;

@Getter
public class Article {

    private Long id;

    private String title;

    private String content;

    private Long recommendCount;

    private Long commentCount;

    private Long viewCount;

    private Club club;

    private User articleWriter;

    private LocalDateTime createdTime;

    @Builder
    public Article(Long id, String title,
                String content, Long recommendCount,
                Long commentCount, Long viewCount,
                   Club club, User articleWriter,
                   LocalDateTime createdTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.recommendCount = recommendCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
        this.club = club;
        this.articleWriter = articleWriter;
        this.createdTime = createdTime;
    }

    public static Article createArticle(User articleWriter, Club club,
                                     CreateArticleRequest request) {

        return Article.builder()
                .title(request.title())
                .content(request.content())
                .recommendCount(0L)
                .commentCount(0L)
                .viewCount(0L)
                .club(club)
                .articleWriter(articleWriter)
                .build();
    }
}
