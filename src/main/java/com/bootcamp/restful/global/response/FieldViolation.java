package com.bootcamp.restful.global.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FieldViolation(
        String field,
        String message
) {
    public static FieldViolation of(String field, String message){
        return new FieldViolation(field,message);
    }
}
