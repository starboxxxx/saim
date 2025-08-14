package moobean.saim.server.community.board.comment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import moobean.saim.server.community.board.comment.controller.port.CreateCommentService;
import moobean.saim.server.community.board.comment.controller.request.CreateCommentRequest;
import moobean.saim.server.global.security.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "09. Comment")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CreateCommentService createCommentService;

    @Operation(summary = "댓글 작성")
    @PostMapping
    public ResponseEntity<Void> createComment(@AuthenticationPrincipal CustomUserDetails userInfo,
                                              @RequestBody CreateCommentRequest request) {
        createCommentService.createComment(userInfo.user().getId(), request);
        return ResponseEntity.noContent().build();
    }
}
