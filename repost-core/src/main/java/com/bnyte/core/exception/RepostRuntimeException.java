package com.bnyte.core.exception;

/**
 * @auther bnyte
 * @date 2021-08-17 17:30
 * @email bnytezz@163.com
 */
public class RepostRuntimeException extends RuntimeException {

    public RepostRuntimeException() {
    }

    public RepostRuntimeException(String message) {
        super(message);
    }

    public RepostRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepostRuntimeException(Throwable cause) {
        super(cause);
    }

    public RepostRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
