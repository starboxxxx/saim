package moobean.saim.server.community.board.article.controller.response;

import java.time.LocalDateTime;

public record ArticleListResponse(
        Long userId,
        String userName,
        String clubName,
        String content,
        Long recommendCount,
        Long commentCount,
        LocalDateTime createdTime
) {
}
