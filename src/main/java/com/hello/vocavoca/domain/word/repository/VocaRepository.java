package com.hello.vocavoca.domain.word.repository;

import com.hello.vocavoca.domain.studyset.StudySet;
import com.hello.vocavoca.domain.word.Voca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VocaRepository extends JpaRepository<Voca, Long> {
    List<Voca> findByStudySet(StudySet studySet);
}
