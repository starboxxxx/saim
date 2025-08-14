package moobean.saim.server.community.board.recommend.service.port;

import moobean.saim.server.community.board.recommend.domain.Recommend;

import java.util.Optional;

public interface RecommendRepository {

    Recommend findArticleRecommend(Long userId, Long articleId);

    Recommend findCommentRecommend(Long userId, Long commentId);


    void createRecommend(Recommend recommend);

    void dropRecommend(Recommend recommend);
}
