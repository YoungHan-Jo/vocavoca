package com.hello.vocavoca.web.studyset.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudySetSaveForm {

    @NotBlank
    private String title;

    @Length(max = 255)
    private String description;

    List<VocaForm> vocaFormList;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class VocaForm {

        private String word;
        private String meaning;
    }
}

