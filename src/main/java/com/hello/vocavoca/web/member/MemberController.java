package com.hello.vocavoca.web.member;

import com.hello.vocavoca.domain.member.Member;
import com.hello.vocavoca.domain.member.repository.MemberRepository;
import com.hello.vocavoca.web.member.form.AddMemberForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("form") AddMemberForm form) {
        return "members/addMemberForm";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("form") AddMemberForm form, BindingResult bindingResult) {
        log.info("form={}",form);

        if (form.getEmail() != null) {
            Optional<Member> optionalMember = memberRepository.findByEmail(form.getEmail());
            if (optionalMember.isPresent()) {
                bindingResult.rejectValue("email","duplicate", new Object[]{form.getEmail()}, null);
            }
        }

        if (form.getPassword() != null && form.getPasswordConfirm() != null) {
            if (!form.getPassword().equals(form.getPasswordConfirm())) {
                bindingResult.reject("passwordConfirm",null,"비밀번호 확인이 일치하지 않습니다.");
            }
        }

        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }

        saveMember(form);

        return "redirect:/login";
    }

    private void saveMember(AddMemberForm form) {
        String hashPassword = passwordEncoder.encode(form.getPassword());
        Member member = Member.builder()
                .email(form.getEmail())
                .password(hashPassword)
                .name(form.getName())
                .gender(form.getGender())
                .build();
        memberRepository.save(member);
    }

}
