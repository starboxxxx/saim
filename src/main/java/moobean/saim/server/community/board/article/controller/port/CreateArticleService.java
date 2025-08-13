package moobean.saim.server.community.board.article.controller.port;

import moobean.saim.server.community.board.article.controller.request.CreateArticleRequest;

public interface CreateArticleService {

    void create(Long userId, CreateArticleRequest request);
}
