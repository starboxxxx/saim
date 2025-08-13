package moobean.saim.server.community.board.comment.infrastructure;

import moobean.saim.server.community.board.comment.infrastructure.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentJpaRepository extends JpaRepository<CommentEntity, Long> {

    @Query("SELECT c FROM CommentEntity c " +
            "WHERE c.article.id = :articleId " +
            "ORDER BY c.createdTime ASC")
    List<CommentEntity> findByArticleId(@Param("articleId") Long articleId);
}
