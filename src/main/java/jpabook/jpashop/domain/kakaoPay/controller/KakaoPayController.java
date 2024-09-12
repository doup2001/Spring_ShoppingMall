package jpabook.jpashop.domain.kakaoPay.controller;

import jakarta.servlet.http.HttpSession;
import jpabook.jpashop.domain.kakaoPay.dto.KakaoApproveResponse;
import jpabook.jpashop.domain.kakaoPay.service.KakaoPayService;
import jpabook.jpashop.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
@Log4j2
public class KakaoPayController {
    private final KakaoPayService kakaoPayService;
    private final OrderService orderService;

    @GetMapping("/success")
    public String afterPayRequest(@RequestParam("pg_token") String pgToken, HttpSession session) {

        Long orderId = (Long) session.getAttribute("orderId");
        Long memberId = (Long) session.getAttribute("memberId");
        Long itemId = (Long) session.getAttribute("itemId");
        int count = (int) session.getAttribute("count");

        KakaoApproveResponse kakaoApprove = kakaoPayService.approveResponse(pgToken, orderId,memberId);

        if (kakaoApprove.getCid()!=null)
            orderService.order(memberId, itemId, count);

        return "redirect:/orders";
    }

}
