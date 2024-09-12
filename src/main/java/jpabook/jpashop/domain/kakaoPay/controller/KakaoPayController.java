package jpabook.jpashop.domain.kakaoPay.controller;

import jpabook.jpashop.domain.kakaoPay.dto.KakaoApproveResponse;
import jpabook.jpashop.domain.kakaoPay.dto.KakaoCancelResponse;
import jpabook.jpashop.domain.kakaoPay.dto.KakaoReadyResponse;
import jpabook.jpashop.domain.kakaoPay.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
public class KakaoPayController {
    private final KakaoPayService kakaoPayService;

    @GetMapping
    public String home() {
        return "kakaoPay/kakaoPay";
    }

    @ResponseBody
    @PostMapping("/ready")
    public KakaoReadyResponse readyToKakaoPay() {

        return kakaoPayService.kakaoPayReady();
    }
}
