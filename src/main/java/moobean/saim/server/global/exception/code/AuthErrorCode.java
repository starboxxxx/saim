package moobean.saim.server.global.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode{

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH_001", "인증 과정에서 오류가 발생하였습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "AUTH_002", "접근이 거부되었습니다"),
    EMPTY_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_003", "AccessToken이 비어있습니다."),
    EXPIRED_ACCESS_TOKEN(HttpStatus.FORBIDDEN, "AUTH_004", "사용 기간이 만료된 AccessToken입니다."),
    EXPIRED_REFRESH_TOKEN(HttpStatus.FORBIDDEN, "AUTH_005", "사용 기간이 만료된 RefreshToken입니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "AUTH_006", "RefreshToken을 찾을 수 없습니다"),
    REFRESH_TOKEN_MISMATCH(HttpStatus.UNAUTHORIZED, "AUTH_007", "RefreshToken이 일치하지 않습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_008", "유효하지 않은 토큰입니다."),
    MALFORMED_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_009", "비정상적인 토큰입니다."),
    SIGNATURE_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_010", "서명이 조작된 토큰입니다."),
    UNSUPPORTED_JWT_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_011", "지원하지 않는 토큰입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;
}
