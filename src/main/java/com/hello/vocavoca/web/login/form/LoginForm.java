package com.hello.vocavoca.web.login.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;
}
