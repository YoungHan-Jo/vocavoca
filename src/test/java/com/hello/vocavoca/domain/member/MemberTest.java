package com.hello.vocavoca.domain.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    @Test
    void editMember() {
        // given
        Member member = Member.builder()
                .id(1L)
                .email("email@email.com")
                .password("password")
                .name("currentName")
                .gender(Gender.MALE)
                .build();

        Member updateMember = Member.builder()
                .name("new name")
                .gender(Gender.FEMALE)
                .build();

        // when
        member.editInfo(updateMember);

        // then
        assertThat(member.getName()).isEqualTo(updateMember.getName());
        assertThat(member.getGender()).isEqualTo(updateMember.getGender());

    }

}