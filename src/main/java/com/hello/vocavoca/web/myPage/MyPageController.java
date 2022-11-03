package com.hello.vocavoca.web.myPage;

import com.hello.vocavoca.domain.member.Member;
import com.hello.vocavoca.domain.member.repository.MemberRepository;
import com.hello.vocavoca.domain.myPage.MyPageService;
import com.hello.vocavoca.web.argumentResolver.Login;
import com.hello.vocavoca.web.myPage.form.EditMemberForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/myPage")
public class MyPageController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MyPageService myPageService;

    @GetMapping
    public String myPage(@Login Member member, Model model) {

        model.addAttribute("member", member);

        return "myPage/myPage";
    }

    @GetMapping("/edit")
    public String updateForm(@Login Member member, @ModelAttribute("form") EditMemberForm form) {
        if (form.getId() == null) {
            form.setId(member.getId());
            form.setEmail(member.getEmail());
            form.setName(member.getName());
            form.setGender(member.getGender());
        }

        return "myPage/editMemberForm";
    }

    @PostMapping("/edit")
    public String update(@Login Member member,
                         @Valid @ModelAttribute("form") EditMemberForm form,
                         BindingResult bindingResult) {
        log.info("form={}",form);
        log.info("error",bindingResult);

        if (!passwordEncoder.matches(form.getPassword(), member.getPassword())) {
            bindingResult.reject("wrongPassword", "비밀번호가 일치하지 않습니다.");
        }

        if (bindingResult.hasErrors()) {
            return "myPage/editMemberForm";
        }

        Member editMember = generateEditMember(form);
        member.editInfo(editMember);

        return "redirect:/myPage";
    }

    private Member generateEditMember(EditMemberForm form) {
        Member editMember = Member.builder()
                .name(form.getName())
                .gender(form.getGender())
                .build();
        return editMember;
    }
}
