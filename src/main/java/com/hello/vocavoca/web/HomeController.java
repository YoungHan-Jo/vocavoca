package com.hello.vocavoca.web;

import com.hello.vocavoca.domain.member.Member;
import com.hello.vocavoca.web.argumentResolver.Login;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@Login Member member, Model model) {
        log.info("member={}",member);

        if (member != null) {
            model.addAttribute("member", member);
        }

        return "home";
    }

}
