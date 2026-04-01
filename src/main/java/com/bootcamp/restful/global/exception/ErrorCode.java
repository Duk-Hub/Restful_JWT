package com.bootcamp.restful.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
    USERNAME_DUPLICATED(HttpStatus.CONFLICT, "중복된 ID입니다."),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "ID 혹은 패스워드가 틀렸습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"예기치 못한 오류가 발생했습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"로그인이 필요합니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN,"권한이 없습니다."),
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "잘못된 입력값입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다.");

    private final HttpStatus status;
    private final String message;
}
