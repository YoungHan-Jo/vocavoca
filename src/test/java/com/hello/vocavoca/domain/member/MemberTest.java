package com.hello.vocavoca.domain.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void editMember() {
        // given
        Member member = generateMember();

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

    @Test
    void changePassword() {
        //given
        Member member = generateMember();
        String newPassword = "newPassword";
        Member changePasswordMember = Member.builder()
                .password(passwordEncoder.encode(newPassword))
                .build();

        //when
        member.changePassword(changePasswordMember);

        //then
        assertThat(passwordEncoder.matches(newPassword, member.getPassword()));
    }

    private Member generateMember() {
        Member member = Member.builder()
                .id(1L)
                .email("email@email.com")
                .password("password")
                .name("currentName")
                .gender(Gender.MALE)
                .build();
        return member;
    }

}