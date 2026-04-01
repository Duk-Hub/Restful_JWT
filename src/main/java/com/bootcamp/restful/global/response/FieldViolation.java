package com.bootcamp.restful.global.response;

public record FieldViolation(
        String field,
        String message
) {
    public static FieldViolation of(String field, String message){
        return new FieldViolation(field,message);
    }
}
