package moobean.saim.server.community.board.article.service.port;

import moobean.saim.server.community.board.article.domain.Article;

import java.util.List;

public interface ArticleRepository {

    void create(Article article);

    Article find(Long articleId);

    List<Article> findClubArticle(Long clubId);
}
