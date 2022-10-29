package com.hello.vocavoca.web.member;

import com.hello.vocavoca.domain.member.repository.MemberRepository;
import com.hello.vocavoca.web.member.form.AddMemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("form") AddMemberForm form) {
        return "members/addMemberForm";
    }

}
