package moobean.saim.server.global.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FollowErrorCode implements BaseErrorCode{

    FOLLOWER_NOT_FOUND(HttpStatus.NOT_FOUND, "FOLLOW_001", "팔로워를 찾을 수 없습니다."),
    TARGET_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "FOLLOW_002", "팔로우 대상 회원을 찾을 수 없습니다")
    ;

    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;
}
