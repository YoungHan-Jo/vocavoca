package com.hello.vocavoca.web.studyset.repository;

import com.hello.vocavoca.domain.member.Member;
import com.hello.vocavoca.web.studyset.form.StudySetListForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudySetRepositoryCustom {

    Page<StudySetListForm> findStudySetListForm(Pageable pageable, Member member);

}
