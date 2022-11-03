package com.hello.vocavoca.domain.myPage;

import com.hello.vocavoca.domain.member.Member;
import com.hello.vocavoca.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {

    private final MemberRepository memberRepository;

    @Transactional
    public void changePassword(Long memberId, Member changePasswordMember) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        if (optionalMember.isEmpty()) {
            // TODO: 2022-11-03 예외처리
        }

        Member member = optionalMember.get();
        member.changePassword(changePasswordMember);
    }

    @Transactional
    public Member editInfo(Long memberId, Member editMember) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        if (optionalMember.isEmpty()) {
            // TODO: 2022-11-03 예외처리
        }
        Member member = optionalMember.get();
        member.editInfo(editMember);

        return member;
    }
}
