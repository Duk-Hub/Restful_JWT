package com.bootcamp.restful.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUsernameRequest(
        @NotBlank(message = "아이디를 입력해주세요.")
        @Size(min = 3, max = 16, message = "아이디는 3자 이상 16자 이하여야 합니다.")
        @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "아이디는 영문, 숫자, 언더스코어만 사용 가능합니다.")
        String username
) {
}
