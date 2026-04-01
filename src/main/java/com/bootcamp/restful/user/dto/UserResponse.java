package com.bootcamp.restful.user.dto;

import com.bootcamp.restful.user.entity.User;

public record UserResponse(
        Long id,
        String username
) {
    public static UserResponse from(User user) {
        return new UserResponse(user.getId(), user.getUsername());
    }
}
