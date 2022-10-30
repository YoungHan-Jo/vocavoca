package com.hello.vocavoca.web.member.form;

import com.hello.vocavoca.domain.member.Gender;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddMemberForm {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
    @NotBlank
    private String passwordConfirm;

    @NotBlank
    private String name;

    @NotNull
    private Gender gender;

}
