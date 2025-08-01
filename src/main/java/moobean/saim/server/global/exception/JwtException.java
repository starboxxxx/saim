package moobean.saim.server.global.exception;

import moobean.saim.server.global.exception.code.BaseErrorCode;

public class JwtException extends BaseException {
    public JwtException(BaseErrorCode code) {
        super(code);
    }

    public BaseErrorCode getErrorCode() {
      return (BaseErrorCode)super.getCode();
    }
}
