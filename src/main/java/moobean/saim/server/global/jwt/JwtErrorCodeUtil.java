package moobean.saim.server.global.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moobean.saim.server.global.exception.JwtException;
import moobean.saim.server.global.exception.code.AuthErrorCode;
import moobean.saim.server.global.exception.code.BaseErrorCode;

import java.security.SignatureException;
import java.util.Map;
import java.util.Optional;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JwtErrorCodeUtil {

    private static final Map<Class<? extends Exception>, BaseErrorCode> ERROR_CODE_MAP = Map.of(
            ExpiredJwtException.class, AuthErrorCode.EXPIRED_ACCESS_TOKEN,
            MalformedJwtException.class, AuthErrorCode.MALFORMED_TOKEN,
            SignatureException.class, AuthErrorCode.SIGNATURE_TOKEN,
            UnsupportedJwtException.class, AuthErrorCode.UNSUPPORTED_JWT_TOKEN
    );

    public static BaseErrorCode determineErrorCode(Exception exception, BaseErrorCode defaultErrorCode) {
        if (exception instanceof JwtException jwtException)
            return jwtException.getErrorCode();

        Class<? extends Exception> exceptionClass = exception.getClass();
        return ERROR_CODE_MAP.getOrDefault(exceptionClass, defaultErrorCode);
    }

    public static JwtException determineAuthErrorException(Exception exception) {
        return findAuthErrorException(exception).orElseGet(
                () -> {
                    BaseErrorCode errorCode = determineErrorCode(exception, AuthErrorCode.UNAUTHORIZED);
                    log.debug(exception.getMessage(), exception);
                    return new JwtException(errorCode);
                }
        );
    }

    private static Optional<JwtException> findAuthErrorException(Exception exception) {
        if (exception instanceof JwtException) {
            return Optional.of((JwtException)exception);
        } else if (exception.getCause() instanceof JwtException) {
            return Optional.of((JwtException)exception.getCause());
        }
        return Optional.empty();
    }
}
