package com.hello.vocavoca.web.studyset;

import com.hello.vocavoca.domain.studyset.StudySet;
import com.hello.vocavoca.domain.studyset.repository.StudySetRepository;
import com.hello.vocavoca.domain.word.Voca;
import com.hello.vocavoca.domain.word.repository.VocaRepository;
import com.hello.vocavoca.web.pageMaker;
import com.hello.vocavoca.web.studyset.form.StudySetListForm;
import com.hello.vocavoca.web.studyset.form.StudySetSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/studySets")
public class StudySetController {

    private final StudySetRepository studySetRepository;
    private final VocaRepository vocaRepository;

    @GetMapping
    public String list(Pageable pageable, Model model) {

        Page<StudySetListForm> formPage = studySetRepository.findStudySetListForm(pageable);

        log.info("currentPage={}", formPage.getNumber());
        log.info("totalPages={}", formPage.getTotalPages());

        pageMaker pageMaker = new pageMaker(formPage.getNumber(), formPage.getTotalPages());

        log.info("pageMaker={}", pageMaker);

        model.addAttribute("formPage", formPage);
        model.addAttribute("pageMaker", pageMaker);

        return "studySets/studySets";
    }

    @GetMapping("/{studySetId}")
    public String studySet(@PathVariable Long studySetId, Model model) {

        Optional<StudySet> optionalStudySet = studySetRepository.findById(studySetId);
        if (optionalStudySet.isEmpty()) {
            // TODO: 2022-11-01  exception
        }

        StudySet studySet = optionalStudySet.get();
        List<Voca> vocas = vocaRepository.findByStudySet(studySet);

        model.addAttribute("studySet", studySet);
    model.addAttribute("vocas", vocas);

        return "studySets/studySet";
    }


    @GetMapping("/add")
    public String addForm(@ModelAttribute("form") StudySetSaveForm form) {


        log.info("form = {}", form);

        return "studySets/addStudySetForm";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("form") StudySetSaveForm form) {

        return "studySets/myStudySets";
    }



}
