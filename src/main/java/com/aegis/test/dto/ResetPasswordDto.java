package com.aegis.test.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordDto {
    private String email;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

}