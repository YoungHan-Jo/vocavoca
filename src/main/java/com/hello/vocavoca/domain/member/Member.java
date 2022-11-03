package com.hello.vocavoca.domain.member;

import com.hello.vocavoca.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder @Getter
@Entity
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;


    public void editInfo(Member updateMember) {
        name = updateMember.getName();
        gender = updateMember.getGender();
    }
}
