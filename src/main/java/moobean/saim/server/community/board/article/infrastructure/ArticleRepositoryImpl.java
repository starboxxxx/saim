package moobean.saim.server.community.board.article.infrastructure;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.board.article.domain.Article;
import moobean.saim.server.community.board.article.infrastructure.entity.ArticleEntity;
import moobean.saim.server.community.board.article.service.port.ArticleRepository;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.ArticleErrorCode;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository {

    private final ArticleJpaRepository repository;

    @Override
    public Article find(Long articleId) {
        return repository.findById(articleId)
                .orElseThrow(() -> new ApplicationException(ArticleErrorCode.ARTICLE_NOT_FOUND))
                .toModel();
    }

    @Override
    public List<Article> findClubArticle(Long clubId) {
        return repository.findClubArticle(clubId).stream()
                .map(ArticleEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void create(Article article) {
        repository.save(ArticleEntity.from(article));
    }
}
