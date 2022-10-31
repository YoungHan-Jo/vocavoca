package com.hello.vocavoca.domain.studyset.repository;

import com.hello.vocavoca.domain.studyset.StudySet;
import com.hello.vocavoca.web.studyset.repository.StudySetRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudySetRepository extends JpaRepository<StudySet, Long>, StudySetRepositoryCustom {

}
