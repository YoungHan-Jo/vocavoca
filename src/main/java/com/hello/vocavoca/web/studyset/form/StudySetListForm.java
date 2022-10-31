package com.hello.vocavoca.web.studyset.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudySetListForm {

    private Long id;

    private String title;

    private Long count;

    private String writer;

}
