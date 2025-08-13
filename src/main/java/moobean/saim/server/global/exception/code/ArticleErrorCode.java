package moobean.saim.server.global.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ArticleErrorCode implements BaseErrorCode{

    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE_001", "해당 게시글을 찾을 수 없습니다"),
    NO_AUTHORITY(HttpStatus.BAD_REQUEST, "ARTICLE_002", "해당 게시글을 읽을 권한이 없습니다")
    ;

    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;
}
