package moobean.saim.server.community.board.recommend.infrastructure;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.board.recommend.domain.Recommend;
import moobean.saim.server.community.board.recommend.infrastructure.entity.RecommendEntity;
import moobean.saim.server.community.board.recommend.service.port.RecommendRepository;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.RecommendErrorCode;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RecommendRepositoryImpl implements RecommendRepository {

    private final RecommendJpaRepository recommendJpaRepository;

    @Override
    public Recommend findArticleRecommend(Long userId, Long articleId) {
        return recommendJpaRepository.findArticleRecommend(userId, articleId)
                .orElseThrow(() -> new ApplicationException(RecommendErrorCode.RECOMMEND_NOT_FOUND))
                .toModel();
    }

    @Override
    public Recommend findCommentRecommend(Long userId, Long commentId) {
        return recommendJpaRepository.findCommentRecommend(userId, commentId)
                .orElseThrow(() -> new ApplicationException(RecommendErrorCode.RECOMMEND_NOT_FOUND))
                .toModel();
    }

    @Override
    public void createRecommend(Recommend recommend) {
        recommendJpaRepository.save(RecommendEntity.from(recommend));
    }

    @Override
    public void dropRecommend(Recommend recommend) {
        recommendJpaRepository.deleteById(recommend.getId());
    }
}
