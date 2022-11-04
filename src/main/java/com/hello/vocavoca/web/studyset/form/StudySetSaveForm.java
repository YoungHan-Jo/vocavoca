package com.hello.vocavoca.web.studyset.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudySetSaveForm implements StudySetForm{

    @NotBlank
    private String title;

    @Length(max = 255)
    private String description;

    private List<VocaForm> vocaFormList;

}

