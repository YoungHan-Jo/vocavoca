package com.hello.vocavoca.web.member.form;

import com.hello.vocavoca.domain.member.Gender;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AddMemberForm {

    @Email
    private String email;

    @NotEmpty
    private String password;
    @NotEmpty
    private String passwordConfirm;

    @NotEmpty
    private String name;

    @NotNull
    private Gender gender;

}
