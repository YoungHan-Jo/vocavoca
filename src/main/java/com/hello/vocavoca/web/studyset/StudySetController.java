package com.hello.vocavoca.web.studyset;

import com.hello.vocavoca.domain.member.Member;
import com.hello.vocavoca.domain.studyset.StudySet;
import com.hello.vocavoca.domain.studyset.StudySetService;
import com.hello.vocavoca.domain.studyset.repository.StudySetRepository;
import com.hello.vocavoca.domain.word.Voca;
import com.hello.vocavoca.domain.word.repository.VocaRepository;
import com.hello.vocavoca.web.argumentResolver.Login;
import com.hello.vocavoca.web.pageMaker;
import com.hello.vocavoca.web.studyset.form.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/studySets")
public class StudySetController {

    private final StudySetRepository studySetRepository;
    private final VocaRepository vocaRepository;

    private final StudySetService studySetService;

    @GetMapping
    public String list(Pageable pageable, Model model) {

        Page<StudySetListForm> formPage = studySetRepository.findStudySetListForm(pageable, null);

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

        if (form.getVocaFormList() == null) {
            form.setVocaFormList(new ArrayList<>());
            form.getVocaFormList().add(new VocaForm("", ""));
        }

//        form.setTitle("제목");
//        form.setDescription("설명");
//        form.getWordFormList().add(new StudySetSaveForm.WordForm("word1", "meaning1"));
//        form.getWordFormList().add(new StudySetSaveForm.WordForm("word2", "meaning2"));

        log.info("form = {}", form);


        return "studySets/addStudySetForm";
    }

    @PostMapping("/add")
    public String add(@Login Member member,
                      @Valid @ModelAttribute("form") StudySetSaveForm form,
                      BindingResult bindingResult) {
        filterEmptyIndex(form);

        if (bindingResult.hasErrors()) {
            return "studySets/addStudySetForm";
        }

        StudySet studySet = getStudySet(member, form);
        List<Voca> vocaList = getVocaList(form, studySet);
        studySetService.addStudySet(studySet, vocaList);

        return "redirect:/myPage/studySets?page=0&size=6";
    }

    @GetMapping("/{studySetId}/edit")
    public String editForm(@PathVariable Long studySetId,
                           @ModelAttribute("form") StudySetEditForm form) {

        log.info("/studySets/{id}/edit");

        Optional<StudySet> optionalStudySet = studySetRepository.findById(studySetId);
        if (optionalStudySet.isEmpty()) {
            // TODO: 2022-11-04 예외처리
        }
        StudySet studySet = optionalStudySet.get();

        List<Voca> vocaList = vocaRepository.findByStudySet(studySet);

        List<VocaForm> vocaForms = new ArrayList<>();
        vocaList.stream().forEach(voca ->
                vocaForms.add(VocaForm.builder()
                        .word(voca.getWord())
                        .meaning(voca.getMeaning())
                        .build())
        );

        form.mapper(studySet, vocaForms);

        return "studySets/editStudySetForm";
    }

    @PostMapping("/{studySetId}/edit")
    public String edit(@PathVariable Long studySetId,
                       @Valid @ModelAttribute("form") StudySetEditForm form,
                       BindingResult bindingResult) {
        filterEmptyIndex(form);

        if (bindingResult.hasErrors()) {
            return "studySets/editStudySetForm";
        }

        return "redirect:/studySets/{studySetId}";

    }

    private StudySet getStudySet(Member member, StudySetSaveForm form) {
        StudySet studySet = StudySet.builder()
                .member(member)
                .title(form.getTitle())
                .description(form.getDescription())
                .build();
        return studySet;
    }

    private List<Voca> getVocaList(StudySetSaveForm form, StudySet studySet) {
        List<Voca> vocaList = new ArrayList<>();

        form.getVocaFormList().stream().forEach(vocaForm ->
                vocaList.add(Voca.builder()
                                .studySet(studySet)
                                .word(vocaForm.getWord())
                                .meaning(vocaForm.getMeaning())
                                .build())
        );
        return vocaList;
    }

    private void filterEmptyIndex(StudySetForm form) {
        List<VocaForm> vocaFormList = form.getVocaFormList();
        vocaFormList = vocaFormList.stream()
                .filter(vocaForm -> vocaForm.getWord() != null || vocaForm.getMeaning() != null)
                .filter(vocaForm -> !vocaForm.getWord().isEmpty() || !vocaForm.getMeaning().isEmpty())
                .collect(Collectors.toList());
        form.setVocaFormList(vocaFormList);
    }


}
