package moobean.saim.server.community.board.recommend.service;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.board.article.domain.Article;
import moobean.saim.server.community.board.article.service.domain.ArticleService;
import moobean.saim.server.community.board.comment.domain.Comment;
import moobean.saim.server.community.board.comment.service.domain.CommentService;
import moobean.saim.server.community.board.recommend.controller.port.CreateRecommendService;
import moobean.saim.server.community.board.recommend.controller.port.DropRecommendService;
import moobean.saim.server.community.board.recommend.domain.Recommend;
import moobean.saim.server.community.board.recommend.service.port.RecommendRepository;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.service.domain.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements CreateRecommendService, DropRecommendService {

    private final RecommendRepository recommendRepository;
    private final UserService userService;
    private final ArticleService articleService;
    private final CommentService commentService;

    @Override
    public void createArticleRecommend(Long userId, Long articleId) {
        User recommender = userService.find(userId);
        Article article = articleService.find(articleId);
        Recommend recommend = Recommend.createArticleRecommend(article, recommender);

        recommendRepository.createRecommend(recommend);
    }

    @Override
    public void createCommentRecommend(Long userId, Long commentId) {
        User recommender = userService.find(userId);
        Comment comment = commentService.findComment(commentId);

        Recommend recommend = Recommend.createCommentRecommend(comment, recommender);

        recommendRepository.createRecommend(recommend);
    }

    @Override
    public void dropArticleRecommend(Long userId, Long articleId) {
        Recommend recommend = recommendRepository.findArticleRecommend(userId, articleId);
        recommendRepository.dropRecommend(recommend);
    }

    @Override
    public void dropCommentRecommend(Long userId, Long commentId) {
        Recommend recommend = recommendRepository.findCommentRecommend(userId, commentId);
        recommendRepository.dropRecommend(recommend);
    }
}
