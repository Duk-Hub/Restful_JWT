package com.bootcamp.restful.user.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {
}
