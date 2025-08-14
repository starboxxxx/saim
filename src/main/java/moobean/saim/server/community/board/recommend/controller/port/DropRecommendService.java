package moobean.saim.server.community.board.recommend.controller.port;

public interface DropRecommendService {
    void dropArticleRecommend(Long userId, Long articleId);

    void dropCommentRecommend(Long userId, Long commentId);
}
