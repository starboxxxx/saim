package moobean.saim.server.community.board.recommend.infrastructure;

import moobean.saim.server.community.board.recommend.infrastructure.entity.RecommendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RecommendJpaRepository extends JpaRepository<RecommendEntity, Long> {

    @Query("SELECT r FROM RecommendEntity r " +
            "WHERE r.recommender.id = :userId AND r.article.id = :articleId")
    Optional<RecommendEntity> findArticleRecommend(@Param("userId") Long userId,
                                            @Param("articleId") Long articleId);

    @Query("SELECT r FROM RecommendEntity r " +
            "WHERE r.recommender.id = :userId AND r.comment.id = :commentId")
    Optional<RecommendEntity> findCommentRecommend(@Param("userId") Long userId,
                                                   @Param("commentId") Long commentId);
}
