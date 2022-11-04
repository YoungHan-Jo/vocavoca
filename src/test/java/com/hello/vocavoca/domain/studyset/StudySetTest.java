package com.hello.vocavoca.domain.studyset;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StudySetTest {

    @Test
    void edit() {
        //given
        StudySet studySet = StudySet.builder()
                .title("title")
                .description("description")
                .build();

        StudySet updateStudySet = StudySet.builder()
                .title("update title")
                .description("update title")
                .build();
        //when
        studySet.edit(updateStudySet);

        //then
        assertThat(studySet.getTitle()).isEqualTo(updateStudySet.getTitle());
        assertThat(studySet.getDescription()).isEqualTo(updateStudySet.getDescription());
    }

}