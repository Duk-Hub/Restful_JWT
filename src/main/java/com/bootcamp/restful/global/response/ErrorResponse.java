package com.bootcamp.restful.global.response;

import java.util.List;

public record ErrorResponse(
        String code,
        String message,
        List<FieldViolation> fieldErrors
) {
    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code, message, null);
    }

    public static ErrorResponse of(String code, String message, List<FieldViolation> fieldErrors) {
        return new ErrorResponse(code, message, fieldErrors);
    }
}
