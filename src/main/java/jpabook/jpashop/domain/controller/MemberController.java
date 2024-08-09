package jpabook.jpashop.domain.controller;


import jpabook.jpashop.domain.dto.MemberFormDto;
import jpabook.jpashop.domain.entity.Address;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class MemberController {

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberFormDto());
        return "members/createMemberForm";
    }

//    @PostMapping("/members/new")
//    public String createMember(MemberFormDto form) {
//        form.getId();
//        return "redirect:/members/new";
//    }
}
