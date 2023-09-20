package com.elkased.todoapi.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public record AuthenticationProperties(@Value("${auth.secret}") String secretKey,
                                       @Value("${auth.expiration}") Long expirationPeriod) {
}
