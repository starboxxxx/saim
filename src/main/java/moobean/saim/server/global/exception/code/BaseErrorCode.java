package moobean.saim.server.global.exception.code;

import org.springframework.http.HttpStatus;

public interface BaseErrorCode {
    String name();
    HttpStatus getHttpStatus();
    String getCustomCode();
    String getMessage();
}
