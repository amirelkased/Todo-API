package com.elkased.todoapi.dto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProperties {

    @Value("${auth.secret}")
    private String secretKey;

    @Value("${auth.expiration}")
    private Long expirationPeriod;

    public AuthenticationProperties() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Long getExpirationPeriod() {
        return expirationPeriod;
    }

    public void setExpirationPeriod(Long expirationPeriod) {
        this.expirationPeriod = expirationPeriod;
    }
}
