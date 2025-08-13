package moobean.saim.server.community.board.article.service.domain;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.board.article.domain.Article;
import moobean.saim.server.community.board.article.service.port.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public Article find(Long articleId) {
        return articleRepository.find(articleId);
    }
}
