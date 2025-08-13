package moobean.saim.server.community.board.comment.domain.mapper;

import moobean.saim.server.community.board.comment.controller.response.CommentListResponse;
import moobean.saim.server.community.board.comment.domain.Comment;
import moobean.saim.server.user.domain.User;

public class CommentResponseMapper {

    public static CommentListResponse toCommentListResponse(User commentWriter, Comment comment) {
        return new CommentListResponse(
                commentWriter.getId(),
                commentWriter.getName(),
                comment.getContent(),
                comment.getCreatedTime()
        );
    }
}
