package com.hello.vocavoca.domain.word;

import com.hello.vocavoca.domain.BaseTimeEntity;
import com.hello.vocavoca.domain.studyset.StudySet;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder @Getter
@Entity
public class Word extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "word_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "study_set_id")
    private StudySet studySet;

    private String word;
    private String meaning;

}
