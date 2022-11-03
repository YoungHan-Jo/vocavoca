package com.hello.vocavoca.web.myPage;

import com.hello.vocavoca.domain.member.Member;
import com.hello.vocavoca.domain.member.repository.MemberRepository;
import com.hello.vocavoca.domain.myPage.MyPageService;
import com.hello.vocavoca.web.SessionConst;
import com.hello.vocavoca.web.argumentResolver.Login;
import com.hello.vocavoca.web.myPage.form.ChangePasswordForm;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

        log.info("name={}",member.getName());
        log.info("gender={}",member.getGender());

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
                         BindingResult bindingResult,
                         HttpServletRequest request) {
        log.info("form={}",form);
        log.info("error",bindingResult);

        if (!form.getPassword().isBlank() && !passwordEncoder.matches(form.getPassword(), member.getPassword())) {
            bindingResult.reject("wrongPassword", "비밀번호가 일치하지 않습니다.");
        }

        if (bindingResult.hasErrors()) {
            return "myPage/editMemberForm";
        }

        Member editedMember = myPageService.editInfo(member.getId(), generateEditMember(form));

        HttpSession session = request.getSession(false);
        session.setAttribute(SessionConst.LOGIN_MEMBER, editedMember);

        return "redirect:/myPage";
    }

    @GetMapping("/password")
    public String changePasswordForm(@ModelAttribute("form") ChangePasswordForm form) {

        return "myPage/changePasswordForm";
    }

    @PostMapping("/password")
    public String changePassword(@Login Member member,
                                 @Valid @ModelAttribute("form") ChangePasswordForm form,
                                 BindingResult bindingResult, HttpServletRequest request) {

        if (!form.getCurrentPassword().isBlank() && !passwordEncoder.matches(form.getCurrentPassword(), member.getPassword())) {
            bindingResult.reject("wrongPassword", "비밀번호가 일치하지 않습니다.");
        }

        if (!form.getNewPassword().equals(form.getNewPasswordConfirm())) {
            bindingResult.reject("passwordConfirm",null,"비밀번호 확인이 일치하지 않습니다.");
        }

        if (bindingResult.hasErrors()) {
            return "myPage/changePasswordForm";
        }

        myPageService.changePassword(member.getId(), changePasswordMember(form));
        invalidateSession(request);

        return "myPage/changePasswordAlert";
    }

    private Member changePasswordMember(ChangePasswordForm form) {
        return Member.builder()
                .password(passwordEncoder.encode(form.getNewPassword()))
                .build();
    }

    private void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    private Member generateEditMember(EditMemberForm form) {
        Member editMember = Member.builder()
                .name(form.getName())
                .gender(form.getGender())
                .build();
        return editMember;
    }

}
