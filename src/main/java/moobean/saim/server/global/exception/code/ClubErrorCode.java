package moobean.saim.server.global.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ClubErrorCode implements BaseErrorCode {

    CLUB_NOT_FOUND(HttpStatus.NOT_FOUND, "CLUB_001", "해당 클럽을 찾을 수 없습니다"),
    ;

    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;
}
