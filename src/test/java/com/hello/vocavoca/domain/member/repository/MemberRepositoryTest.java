package com.hello.vocavoca.domain.member.repository;

import com.hello.vocavoca.domain.member.Gender;
import com.hello.vocavoca.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void baseTimeEntityTest() {
        // given
        LocalDateTime now = LocalDateTime.now();
        String email = "test@test.com";
        memberRepository.save(
                Member.builder()
                        .email(email)
                        .name("test")
                        .password("password")
                        .gender(Gender.MALE)
                        .build());

        // when
        Member member = memberRepository.findByEmail(email).get();

        // then
        assertThat(member.getCreatedDate()).isAfter(now);
        assertThat(member.getModifiedDate()).isAfter(now);
    }

}