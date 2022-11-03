package com.hello.vocavoca.web.myPage.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class ChangePasswordForm {

    @NotBlank
    private String currentPassword;

    @NotBlank
    @Length(min = 8, max = 15)
    private String newPassword;

    @NotBlank
    private String newPasswordConfirm;
}
