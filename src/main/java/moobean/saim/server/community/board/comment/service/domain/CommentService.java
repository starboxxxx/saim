package moobean.saim.server.community.board.comment.service.domain;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.board.comment.controller.response.CommentListResponse;
import moobean.saim.server.community.board.comment.domain.Comment;
import moobean.saim.server.community.board.comment.domain.mapper.CommentResponseMapper;
import moobean.saim.server.community.board.comment.service.port.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment findComment(Long commentId) {
        return commentRepository.findComment(commentId);
    }

    public List<CommentListResponse> findComments(Long articleId) {
        return commentRepository.findComments(articleId).stream()
                .map(comment -> CommentResponseMapper.toCommentListResponse(
                        comment.getCommentWriter(), comment
                ))
                .collect(Collectors.toList());
    }
}
