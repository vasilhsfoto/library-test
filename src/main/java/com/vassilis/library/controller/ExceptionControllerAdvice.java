package com.vassilis.library.controller;

import com.vassilis.library.exception.WebAppException;
import com.vassilis.library.representation.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    private static final String MESSAGE = "Exception {} encountered while processing req {}";

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleException(Exception exception, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error(MESSAGE,
                exception.getClass().getSimpleName(),
                requestURI,
                exception);
        ErrorResponse response = new ErrorResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleWebAppException(WebAppException exception, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error(MESSAGE,
                exception.getClass().getSimpleName(),
                requestURI,
                exception);
        ErrorResponse response = new ErrorResponse(exception.getMessage(), exception.getHttpStatus());
        return ResponseEntity
                .status(exception.httpStatus)
                .body(response);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleClientAbortException(ClientAbortException exception, HttpServletRequest req) {
        log.error(MESSAGE, exception.getClass().getSimpleName(), req.getContextPath());
        ErrorResponse response = new ErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ResponseEntity<Object> result = super.handleExceptionInternal(ex, body, headers, status, request);
        log.error(MESSAGE,
                ex.getClass().getSimpleName(),
                request.getContextPath(),
                ex);

        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), status);
        return ResponseEntity
                .status(status)
                .body(errorResponse);
    }
}
