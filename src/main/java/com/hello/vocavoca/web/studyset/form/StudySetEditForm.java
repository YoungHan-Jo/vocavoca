package com.hello.vocavoca.web.studyset.form;

import com.hello.vocavoca.domain.studyset.StudySet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudySetEditForm implements StudySetForm{

    private Long studySetId;

    @NotBlank
    private String title;

    @Length(max = 255)
    private String description;

    private List<VocaForm> vocaFormList = new ArrayList<>();

    public void mapper(StudySet studySet, List<VocaForm> vocaForms) {
        studySetId = studySet.getId();
        title = studySet.getTitle();
        description = studySet.getDescription();
        vocaFormList = vocaForms;
    }
}
