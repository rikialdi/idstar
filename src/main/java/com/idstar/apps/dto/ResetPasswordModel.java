package com.idstar.apps.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
public class ResetPasswordModel {
    public String username;
    public String otp;
    public String newPassword;
}

