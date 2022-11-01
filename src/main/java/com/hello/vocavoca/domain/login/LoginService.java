package com.hello.vocavoca.domain.login;

import com.hello.vocavoca.domain.member.Member;
import com.hello.vocavoca.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public Member login(String email, String password) {
        return memberRepository.findByEmail(email)
                .filter(m -> passwordEncoder.matches(password, m.getPassword()))
                .orElse(null);
    }


}
