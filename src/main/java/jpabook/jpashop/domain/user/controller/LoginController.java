package jpabook.jpashop.domain.user.controller;

import jakarta.servlet.http.HttpSession;
import jpabook.jpashop.domain.dto.LoginDto;
import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginP(Model model) {

        model.addAttribute("loginDto", new LoginDto());

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto loginDto, HttpSession httpSession, Model model) {

        Member member = memberService.findByUsername(loginDto.getUsername());

        if (member!= null) {
            httpSession.setAttribute("username", member.getUsername());
            httpSession.setAttribute("city", member.getAddress().getCity());
            httpSession.setAttribute("role", member.getRole());

            return "redirect:/main";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate(); // 세션 무효화하여 로그아웃 처리
        return "redirect:/login"; // 로그아웃 후 로그인 페이지로 리다이렉트
    }
}
