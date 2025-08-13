package moobean.saim.server.community.board.comment.controller.response;

import java.time.LocalDateTime;

public record CommentListResponse(
        Long userId,
        String userName,
        String content,
        LocalDateTime createdTime
) {
}
