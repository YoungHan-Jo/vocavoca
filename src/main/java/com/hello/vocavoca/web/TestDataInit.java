package com.hello.vocavoca.web;

import com.hello.vocavoca.domain.member.Gender;
import com.hello.vocavoca.domain.member.Member;
import com.hello.vocavoca.domain.member.repository.MemberRepository;
import com.hello.vocavoca.domain.studyset.StudySet;
import com.hello.vocavoca.domain.studyset.repository.StudySetRepository;

import com.hello.vocavoca.domain.voca.Voca;
import com.hello.vocavoca.domain.voca.repository.VocaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final StudySetRepository studySetRepository;
    private final VocaRepository vocaRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        Member member1 = generateMember(1);
        Member member2 = generateMember(2);

        IntStream.range(1, 11).forEach(i ->
                generateSampleStudySets(member1, i)
        );
        IntStream.range(11, 21).forEach(i ->
                generateSampleStudySets(member2, i)
        );

    }



    private void generateSampleStudySets(Member member, int i) {
        StudySet studySet = generateStudySet(member, i);
        IntStream.range(1, i + 1).forEach(e ->
                generateWord(studySet, e)
        );
    }

    private StudySet generateStudySet(Member member, int i) {
        StudySet studySet = StudySet.builder()
                .member(member)
                .title("title" + i + "by" + member.getName())
                .description("description" + i)
                .build();
        studySetRepository.save(studySet);
        return studySet;
    }

    private void generateWord(StudySet studySet, int i) {
        Voca voca = Voca.builder()
                .studySet(studySet)
                .word("voca" + i)
                .meaning("meaning" + i)
                .build();
        vocaRepository.save(voca);
    }

    private Member generateMember(int i) {
        Member member = Member.builder()
                .email("test" + i + "@gmail.com")
                .password(passwordEncoder.encode("test"))
                .name("test" + i)
                .gender(Gender.FEMALE)
                .build();
        memberRepository.save(member);
        return member;
    }
}
