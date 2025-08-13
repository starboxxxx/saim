package moobean.saim.server.community.board.article.infrastructure;

import moobean.saim.server.community.board.article.infrastructure.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleJpaRepository extends JpaRepository<ArticleEntity, Long> {

    @Query("SELECT a FROM ArticleEntity a " +
            "WHERE a.club.id = :clubId " +
            "ORDER BY a.createdTime ASC")
    List<ArticleEntity> findClubArticle(@Param("clubId") Long clubId);
}
