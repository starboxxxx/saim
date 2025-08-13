package moobean.saim.server.community.board.comment.service;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.board.article.domain.Article;
import moobean.saim.server.community.board.article.service.domain.ArticleService;
import moobean.saim.server.community.board.comment.controller.port.CreateCommentService;
import moobean.saim.server.community.board.comment.controller.request.CreateCommentRequest;
import moobean.saim.server.community.board.comment.domain.Comment;
import moobean.saim.server.community.board.comment.service.port.CommentRepository;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.service.domain.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CreateCommentService {

    private final UserService userService;
    private final ArticleService articleService;
    private final CommentRepository commentRepository;

    @Override
    public void createComment(Long userId, CreateCommentRequest request) {

        User commentWriter = userService.find(userId);
        Article article = articleService.find(request.articleId());

        Comment comment = Comment.create(commentWriter, article, request.content());
        commentRepository.createComment(comment);
    }
}
