package com.bootcamp.restful.user.dto;

public record MeResponse(
        Long id,
        String username,
        String role
) {
}
