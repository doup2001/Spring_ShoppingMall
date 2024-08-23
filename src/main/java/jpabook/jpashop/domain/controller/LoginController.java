package jpabook.jpashop.domain.controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginP() {

        return "/login/login";
    }

    @GetMapping("/login/loginForm")
    public String login() {

        return "/login/loginForm";
    }
}
