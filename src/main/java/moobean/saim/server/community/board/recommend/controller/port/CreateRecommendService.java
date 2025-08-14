package moobean.saim.server.community.board.recommend.controller.port;

public interface CreateRecommendService {
    void createArticleRecommend(Long userId, Long articleId);

    void createCommentRecommend(Long userId, Long commentId);
}
