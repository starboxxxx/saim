package moobean.saim.server.global.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum RecommendErrorCode implements BaseErrorCode {

    RECOMMEND_NOT_FOUND(HttpStatus.NOT_FOUND, "RECOMMEND_001", "사용자는 해당 게시글을 추천한 적이 없습니다")
    ;
    private final HttpStatus httpStatus;
    private final String customCode;
    private final String message;
}
