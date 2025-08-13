package moobean.saim.server.community.board.comment.controller.port;

import moobean.saim.server.community.board.comment.controller.request.CreateCommentRequest;

public interface CreateCommentService {

    void createComment(Long userId, CreateCommentRequest request);
}
