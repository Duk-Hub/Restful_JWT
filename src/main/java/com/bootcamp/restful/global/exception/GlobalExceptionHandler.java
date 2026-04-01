package com.bootcamp.restful.global.exception;

import com.bootcamp.restful.global.response.ErrorResponse;
import com.bootcamp.restful.global.response.FieldViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e){
        log.warn("Business Exception : {}", e.getErrorCode());
        ErrorCode code = e.getErrorCode();

        return ResponseEntity.status(code.getStatus())
                .body(ErrorResponse.of(code.name(), code.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        ErrorCode code = ErrorCode.INVALID_INPUT;

        List<FieldViolation> fieldErrors = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> FieldViolation.of(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        return ResponseEntity.status(code.getStatus())
                .body(ErrorResponse.of(code.name(),code.getMessage(),fieldErrors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        ErrorCode code = ErrorCode.INTERNAL_SERVER_ERROR;
        log.error("Unhandled Exception", e);

        return ResponseEntity.status(code.getStatus())
                .body(ErrorResponse.of(code.name(),code.getMessage()));
    }
}
