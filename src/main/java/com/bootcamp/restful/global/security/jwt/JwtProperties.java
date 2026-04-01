package com.bootcamp.restful.global.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secret;
    private Duration accessTokenExpiration;
    private Duration refreshTokenExpiration;
}
