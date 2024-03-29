package com.allroundbeauty.server.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String INTERNAL_SERVER_ERROR = "Internal Server Error.";
    public static final int BAD_REQUEST_STATUS = 400;
    public static final int SERVER_ERROR_STATUS = 500;
    public static final int NOT_ACCEPTABLE = 406;

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(BAD_REQUEST_STATUS, e.getMessage()));
    }

    @ExceptionHandler({InternalServerException.class})
    public ResponseEntity<ErrorResponse> handleInternalServerException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.internalServerError().body(new ErrorResponse(SERVER_ERROR_STATUS, e.getMessage()));
    }

    @ExceptionHandler(AlreadyAcceptedException.class)
    private ResponseEntity<ErrorResponse> handleAlreadyAcceptedException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.status(NOT_ACCEPTABLE).body(new ErrorResponse(NOT_ACCEPTABLE, e.getMessage()));
    }

    @ExceptionHandler(NotAcceptedException.class)
    private ResponseEntity<ErrorResponse> handleNotAcceptedException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.status(NOT_ACCEPTABLE).body(new ErrorResponse(NOT_ACCEPTABLE, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception e) {
        log.error("[Internal Server Error]", e);
        return ResponseEntity.internalServerError().body(new ErrorResponse(SERVER_ERROR_STATUS, INTERNAL_SERVER_ERROR));
    }
}
