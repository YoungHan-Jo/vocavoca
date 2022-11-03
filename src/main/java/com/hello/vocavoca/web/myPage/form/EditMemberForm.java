package com.hello.vocavoca.web.myPage.form;

import com.hello.vocavoca.domain.member.Gender;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EditMemberForm {

    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotNull
    private Gender gender;

}
