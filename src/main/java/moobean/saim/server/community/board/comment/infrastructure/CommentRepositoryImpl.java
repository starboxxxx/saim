package moobean.saim.server.community.board.comment.infrastructure;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.board.article.domain.Article;
import moobean.saim.server.community.board.comment.domain.Comment;
import moobean.saim.server.community.board.comment.infrastructure.entity.CommentEntity;
import moobean.saim.server.community.board.comment.service.port.CommentRepository;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.CommentErrorCode;
import moobean.saim.server.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

    private final CommentJpaRepository commentJpaRepository;

    @Override
    public List<Comment> findComments(Long articleId) {
        return commentJpaRepository.findByArticleId(articleId).stream()
                .map(CommentEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Comment findComment(Long commentId) {
        return commentJpaRepository.findById(commentId)
                .orElseThrow(() -> new ApplicationException(CommentErrorCode.COMMENT_NOT_FOUND))
                .toModel();
    }

    @Override
    public void createComment(Comment comment) {
        commentJpaRepository.save(CommentEntity.from(comment));
    }
}
