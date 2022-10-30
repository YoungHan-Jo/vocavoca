package com.hello.vocavoca.web;

import com.hello.vocavoca.domain.member.Gender;
import com.hello.vocavoca.domain.member.Member;
import com.hello.vocavoca.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        Member member = Member.builder()
                .email("test@gmail.com")
                .password(passwordEncoder.encode("test"))
                .name("test")
                .gender(Gender.MALE)
                .build();
        memberRepository.save(member);
    }
}
