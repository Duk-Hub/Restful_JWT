package com.bootcamp.restful.user.service;

import com.bootcamp.restful.global.exception.CustomException;
import com.bootcamp.restful.global.exception.ErrorCode;
import com.bootcamp.restful.global.security.jwt.JwtProvider;
import com.bootcamp.restful.user.dto.LoginRequest;
import com.bootcamp.restful.user.dto.LoginResponse;
import com.bootcamp.restful.user.dto.SignUpRequest;
import com.bootcamp.restful.user.entity.User;
import com.bootcamp.restful.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signUp(SignUpRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new CustomException(ErrorCode.USERNAME_DUPLICATED);
        }

        String encodedPassword = passwordEncoder.encode(request.password());
        userRepository.save(User.join(request.username(), encodedPassword));
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_CREDENTIALS));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_CREDENTIALS);
        }

        String accessToken = jwtProvider.generateAccessToken(user.getId(), user.getRole().name());
        String refreshToken = jwtProvider.generateRefreshToken(user.getId());

        return new LoginResponse(accessToken, refreshToken);
    }
}
