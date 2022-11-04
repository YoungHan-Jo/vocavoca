package com.hello.vocavoca.domain.studyset;

import com.hello.vocavoca.domain.BaseTimeEntity;
import com.hello.vocavoca.domain.member.Member;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder @Getter
@Entity
public class StudySet extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "study_set_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;
    private String description;

    public void edit(StudySet updateStudySet) {
        title = updateStudySet.getTitle();
        description = updateStudySet.getDescription();
    }
}
