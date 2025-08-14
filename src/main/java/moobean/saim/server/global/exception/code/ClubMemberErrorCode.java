package moobean.saim.server.global.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ClubMemberErrorCode implements BaseErrorCode {

    CLUB_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "CLUB_MEMBER_001", "해당 클럽에 존재하지 않는 사용자입니다."),
    NOT_CAPTAIN(HttpStatus.BAD_REQUEST, "CLUB_MEMBER_002", "클럽장 권한이 없습니다"),
    APPROVED_MEMBER(HttpStatus.BAD_REQUEST, "CLUB_MEMBER_003", "이미 가입 승인이 완료된 회원입니다"),
    CAPTAIN_NOT_LEAVE(HttpStatus.BAD_REQUEST, "CLUB_MEMBER_004", "클럽장은 클럽을 나갈 수 없습니다")
    ;

    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;
}
