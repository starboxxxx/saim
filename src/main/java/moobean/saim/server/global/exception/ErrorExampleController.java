package moobean.saim.server.global.exception;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import moobean.saim.server.global.annoation.ApiErrorCodes;
import moobean.saim.server.global.annoation.DevelopOnlyApi;
import moobean.saim.server.global.exception.code.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "*. 에러 응답")
@RestController
@RequestMapping("/api/examples")
public class ErrorExampleController {

    @GetMapping("/global")
    @DevelopOnlyApi
    @Operation(summary = "글로벌(서버 내부 오류 등) 관련 에러 코드 나열")
    @ApiErrorCodes(GlobalErrorCode.class)
    public void getGlobalErrorCode(){}

    @GetMapping("/oAuth")
    @DevelopOnlyApi
    @Operation(summary = "인증 및 인가 관련 에러 코드 나열")
    @ApiErrorCodes(AuthErrorCode.class)
    public void getAuthErrorCode(){}

    @GetMapping("/user")
    @DevelopOnlyApi
    @Operation(summary = "회원 도메인 관련 에러 코드 나열")
    @ApiErrorCodes(UserErrorCode.class)
    public void getUserErrorCode(){}

    @GetMapping("/profile")
    @DevelopOnlyApi
    @Operation(summary = "회원 프로필 도메인 관련 에러 코드 나열")
    @ApiErrorCodes(ProfileErrorCode.class)
    public void getProfileErrorCode(){}

    @GetMapping("/club")
    @DevelopOnlyApi
    @Operation(summary = "클럽 도메인 관련 에러 코드 나열")
    @ApiErrorCodes(ClubErrorCode.class)
    public void getClubErrorCode(){}

    @GetMapping("/clubMember")
    @DevelopOnlyApi
    @Operation(summary = "클럽 회원 도메인 관련 에러 코드 나열")
    @ApiErrorCodes(ClubMemberErrorCode.class)
    public void getClubMemberErrorCode(){}

    @GetMapping("/article")
    @DevelopOnlyApi
    @Operation(summary = "게시글 도메인 관련 에러 코드 나열")
    @ApiErrorCodes(ArticleErrorCode.class)
    public void getArticleErrorCode(){}

    @GetMapping("/follow")
    @DevelopOnlyApi
    @Operation(summary = "팔로우 도메인 관련 에러 코드 나열")
    @ApiErrorCodes(FollowErrorCode.class)
    public void getFollowErrorCode(){}

    @GetMapping("/comment")
    @DevelopOnlyApi
    @Operation(summary = "댓글 도메인 관련 에러 코드 나열")
    @ApiErrorCodes(CommentErrorCode.class)
    public void getCommentErrorCode(){}

    @GetMapping("/recommend")
    @DevelopOnlyApi
    @Operation(summary = "추천 도메인 관련 에러 코드 나열")
    @ApiErrorCodes(RecommendErrorCode.class)
    public void getRecommendErrorCode(){}
}
