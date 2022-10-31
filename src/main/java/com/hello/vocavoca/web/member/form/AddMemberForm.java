package com.hello.vocavoca.web.member.form;

import com.hello.vocavoca.domain.member.Gender;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Data
public class AddMemberForm {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min = 8, max = 15)
    private String password;

    @NotBlank
    private String passwordConfirm;

    @NotBlank
    private String name;

    @NotNull
    private Gender gender;

}
