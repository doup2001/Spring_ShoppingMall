package jpabook.jpashop.domain.controller;

import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;


@Slf4j
@Controller
@RequiredArgsConstructor
public class MyPagecontroller {

    private final MemberService memberService;


    @GetMapping("/mypage")
    public String myP(Model model) {

        //이름 추출
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

//        log.info("[Mylog]"+username);

        // 권한 추출
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        model.addAttribute("username", username);
        model.addAttribute("role",role);


        //        Member member = memberService.findByName(username);
//        log.info("[MyLog" + member);
//        model.addAttribute("member", member);


        return "mypage";
    }
}
