package com.hello.vocavoca.domain.myPage;

import com.hello.vocavoca.domain.member.Member;
import com.hello.vocavoca.domain.member.repository.MemberRepository;
import com.hello.vocavoca.web.exception.MemberException;
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
            throw new MemberException();
        }

        Member member = optionalMember.get();
        member.changePassword(changePasswordMember);
    }

    @Transactional
    public Member editInfo(Long memberId, Member editMember) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        if (optionalMember.isEmpty()) {
            throw new MemberException();
        }
        Member member = optionalMember.get();
        member.editInfo(editMember);

        return member;
    }
}
