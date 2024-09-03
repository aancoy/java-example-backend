package com.aegis.test.response;

import com.aegis.test.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;

    private long expiresIn;

    private User userData; 
}