package com.hello.vocavoca.domain.studyset.repository;

import com.hello.vocavoca.domain.studyset.StudySet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudySetRepository extends JpaRepository<StudySet, Long> {
}
