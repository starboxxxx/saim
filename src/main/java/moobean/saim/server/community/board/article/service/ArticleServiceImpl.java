package moobean.saim.server.community.board.article.service;

import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.board.article.controller.port.CreateArticleService;
import moobean.saim.server.community.board.article.controller.port.FindArticleService;
import moobean.saim.server.community.board.article.controller.request.CreateArticleRequest;
import moobean.saim.server.community.board.article.controller.response.ArticleDetailResponse;
import moobean.saim.server.community.board.article.controller.response.ArticleListResponse;
import moobean.saim.server.community.board.article.controller.response.ArticleResponse;
import moobean.saim.server.community.board.article.controller.response.ClubArticleResponse;
import moobean.saim.server.community.board.article.domain.Article;
import moobean.saim.server.community.board.article.domain.mapper.ArticleResponseMapper;
import moobean.saim.server.community.board.article.service.port.ArticleRepository;
import moobean.saim.server.community.board.comment.controller.response.CommentListResponse;
import moobean.saim.server.community.board.comment.service.domain.CommentService;
import moobean.saim.server.community.club.domain.Club;
import moobean.saim.server.community.club.service.domain.ClubService;
import moobean.saim.server.community.clubMember.service.domain.ClubMemberService;
import moobean.saim.server.global.exception.ApplicationException;
import moobean.saim.server.global.exception.code.ArticleErrorCode;
import moobean.saim.server.user.domain.User;
import moobean.saim.server.user.service.domain.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements CreateArticleService, FindArticleService {

    private final ArticleRepository articleRepository;

    private final UserService userService;
    private final ClubService clubService;
    private final ClubMemberService clubMemberService;
    private final CommentService commentService;

    @Transactional
    @Override
    public ArticleDetailResponse findArticle(Long userId, Long articleId) {
        Article article = articleRepository.find(articleId);

        Boolean isMember = clubMemberService.checkClubMember(userId, article.getClub().getId());

        Boolean hasAuthority = clubService.checkAuthority(isMember, article.getClub().getId());

        if(!hasAuthority) {
            throw new ApplicationException(ArticleErrorCode.NO_AUTHORITY);
        }

        ArticleResponse articleResponse = ArticleResponseMapper.toArticleResponse(
                article.getArticleWriter(),
                article
        );

        List<CommentListResponse> commentList = commentService.findComments(articleId);

        return ArticleResponseMapper.toArticleDetailResponse(articleResponse, commentList);
    }

    @Override
    public ClubArticleResponse findClubArticle(Long userId, Long clubId) {
        Boolean isMember = clubMemberService.checkClubMember(userId, clubId);
        Boolean hasAuthority = clubService.checkAuthority(isMember, clubId);

        if (!hasAuthority) {
            return ArticleResponseMapper.toClubArticleResponse(true, null);
        }

        List<ArticleListResponse> clubArticle =
                articleRepository.findClubArticle(clubId).stream()
                        .map(article -> ArticleResponseMapper.toArticleListResponse(
                                article.getArticleWriter(), article
                        ))
                        .toList();

        return ArticleResponseMapper.toClubArticleResponse(false, clubArticle);

    }

    @Transactional
    @Override
    public void create(Long userId, CreateArticleRequest request) {
        User writer = userService.find(userId);
        Club club = clubService.find(request.clubId());

        Article article = Article.createArticle(writer, club, request);
        articleRepository.create(article);
    }
}
