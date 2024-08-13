package jpabook.jpashop.domain.controller;


import jakarta.validation.Valid;
import jpabook.jpashop.domain.dto.MemberFormDto;
import jpabook.jpashop.domain.entity.Address;
import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import jpabook.jpashop.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberFormDto());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String createMember(@Valid @ModelAttribute MemberFormDto memberForm, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode()));
        memberService.join(member);

        return "redirect:/members";
    }

    @GetMapping
    public String list(Model model) {

        List<Member> members = memberService.findByALl();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
