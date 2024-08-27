package jpabook.jpashop.domain.user.controller;

import jpabook.jpashop.domain.dto.JoinDto;
import jpabook.jpashop.domain.entity.Address;
import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    @GetMapping("/new")
//    public String createForm(Model model) {
//        model.addAttribute("memberForm", new MemberFormDto());
//        return "members/joinMemberForm.html";
//    }
//
//    @PostMapping("/new")
//    public String createMember(@Valid @ModelAttribute MemberFormDto memberForm, BindingResult result) {
//
//        if (result.hasErrors()) {
//            return "members/joinMemberForm.html";
//        }
//
//        // Member 객체 생성 및 설정
//        Member member = new Member();
//        member.setName(memberForm.getName());
//        member.setAddress(new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode()));
//
//        // 회원 가입 처리
//        memberService.join(member);
//
//        return "redirect:/members";
//    }


    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("JoinDto", new JoinDto());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String createMember(@ModelAttribute JoinDto joinDto, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        memberService.join(joinDto);

        return "login";
    }

    @GetMapping
    public String list(Model model) {

        // 모든 회원 목록 조회
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);

        return "members/memberList";
    }

    @PostMapping("/delete/{memberId}")
    public String delete(@PathVariable Long memberId) {
        memberService.delete(memberId);
        return "redirect:/members/0";
    }



//    //로직
//    private Member getMember(JoinDto joinDto) {
//        Member member = new Member();
//        member.setUser_id(joinDto.getUsername());
//        member.setEmail(joinDto.getEmail());
//        member.setName(joinDto.getName());
//        member.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));
//        member.setAddress(new Address(joinDto.getCity(), joinDto.getStreet(), joinDto.getZipcode()));
//        return member;
//    }

}
