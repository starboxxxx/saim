package moobean.saim.server.community.board.comment.service.port;

import moobean.saim.server.community.board.comment.domain.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> findComments(Long articleId);

    Comment findComment(Long commentId);

    void createComment(Comment comment);
}
