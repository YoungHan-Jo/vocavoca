package com.hello.vocavoca.domain.studyset;

import com.hello.vocavoca.domain.studyset.repository.StudySetRepository;
import com.hello.vocavoca.domain.word.Voca;
import com.hello.vocavoca.domain.word.repository.VocaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StudySetService {

    private final StudySetRepository studySetRepository;
    private final VocaRepository vocaRepository;


    @Transactional
    public void addStudySet(StudySet studySet, List<Voca> vocaList) {
        studySetRepository.save(studySet);
        vocaRepository.saveAll(vocaList);
    }

    @Transactional
    public void editStudySet(Long studySetId, StudySet updateStudySet, List<Voca> vocaList) {
        StudySet studySet = studySetRepository.findById(studySetId).get();

        vocaRepository.deleteAllByStudySet(studySet);

        studySet.edit(updateStudySet);
        vocaRepository.saveAll(vocaList);
    }
}
