package moobean.saim.server.community.board.article.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.board.article.controller.port.CreateArticleService;
import moobean.saim.server.community.board.article.controller.port.FindArticleService;
import moobean.saim.server.community.board.article.controller.request.CreateArticleRequest;
import moobean.saim.server.community.board.article.controller.response.ArticleDetailResponse;
import moobean.saim.server.community.board.article.controller.response.ClubArticleResponse;
import moobean.saim.server.global.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "08. Article")
@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {

    private final FindArticleService findArticleService;
    private final CreateArticleService createArticleService;

    @Operation(summary = "게시글 조회")
    @Parameter(name = "articleId", description = "조회할 게시글 ID", in = ParameterIn.PATH)
    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleDetailResponse> getArticle(@AuthenticationPrincipal CustomUserDetails userInfo,
                                                            @PathVariable Long articleId) {
        return ResponseEntity.ok(findArticleService.findArticle(userInfo.user().getId(), articleId));
    }

    @Operation(summary = "클럽에 있는 게시글 목록 조회")
    @Parameter(name = "clubId", description = "게시글 목록 조회할 클럽 ID", in = ParameterIn.PATH)
    @GetMapping("/{clubId}")
    public ResponseEntity<ClubArticleResponse> getClubArticle(@AuthenticationPrincipal CustomUserDetails userInfo,
                                                              @PathVariable Long clubId) {
        return ResponseEntity.ok(findArticleService.findClubArticle(userInfo.user().getId(), clubId));
    }

    @Operation(summary = "게시글 작성")
    @PostMapping
    public ResponseEntity<Void> createArticle(@AuthenticationPrincipal CustomUserDetails userInfo,
                              @RequestBody CreateArticleRequest request) {
        createArticleService.create(userInfo.user().getId(), request);
        return ResponseEntity.noContent().build();
    }
}
