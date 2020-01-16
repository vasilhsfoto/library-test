package com.vassilis.library.exception;

import org.springframework.http.HttpStatus;

public class WebAppExceptionFactory {

    public static WebAppException getNotFoundError(String message) {
        return new WebAppException(message, HttpStatus.NOT_FOUND);
    }
}
