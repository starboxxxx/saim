package moobean.saim.server.community.board.recommend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.board.recommend.controller.port.CreateRecommendService;
import moobean.saim.server.community.board.recommend.controller.port.DropRecommendService;
import moobean.saim.server.global.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "10. Recommend")
@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
public class RecommendController {

    private final CreateRecommendService createRecommendService;
    private final DropRecommendService dropRecommendService;

    @Operation(summary = "게시글 추천")
    @Parameter(name = "articleId", description = "추천할 게시글 ID", in = ParameterIn.PATH)
    @PostMapping("/article/{articleId}")
    public ResponseEntity<Void> createArticleRecommend(@AuthenticationPrincipal CustomUserDetails userInfo,
                                                       @PathVariable Long articleId) {
        createRecommendService.createArticleRecommend(userInfo.user().getId(), articleId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "댓글 추천")
    @Parameter(name = "commentId", description = "추천할 댓글 ID", in = ParameterIn.PATH)
    @PostMapping("/comment/{commentId}")
    public ResponseEntity<Void> createCommentRecommend(@AuthenticationPrincipal CustomUserDetails userInfo,
                                                       @PathVariable Long commentId) {
        createRecommendService.createCommentRecommend(userInfo.user().getId(), commentId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "게시글 추천 취소")
    @Parameter(name = "articleId", description = "추천 취소할 게시글 ID", in = ParameterIn.PATH)
    @DeleteMapping("/article/{articleId}")
    public ResponseEntity<Void> deleteArticleRecommend(@AuthenticationPrincipal CustomUserDetails userInfo,
                                                       @PathVariable Long articleId) {
        dropRecommendService.dropArticleRecommend(userInfo.user().getId(), articleId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "댓글 추천 취소")
    @Parameter(name = "commentId", description = "추천 취소할 댓글 ID", in = ParameterIn.PATH)
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Void> deleteCommentRecommend(@AuthenticationPrincipal CustomUserDetails userInfo,
                                                       @PathVariable Long commentId) {
        dropRecommendService.dropCommentRecommend(userInfo.user().getId(), commentId);
        return ResponseEntity.noContent().build();
    }
}
