package com.hello.vocavoca.domain.voca.repository;

import com.hello.vocavoca.domain.studyset.StudySet;
import com.hello.vocavoca.domain.voca.Voca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VocaRepository extends JpaRepository<Voca, Long> {
    List<Voca> findByStudySet(StudySet studySet);

    void deleteAllByStudySet(StudySet studySet);
}
