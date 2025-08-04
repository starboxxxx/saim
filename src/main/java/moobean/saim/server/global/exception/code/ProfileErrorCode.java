package moobean.saim.server.global.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ProfileErrorCode implements BaseErrorCode{

    PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "PROFILE_001", "사용자 프로필을 찾을 수 없습니다"),
    TRAINER_INVALID(HttpStatus.BAD_REQUEST, "PROFILE_002", "존재하지 않는 트레이너입니다")
    ;

    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;
}
