package jpabook.jpashop.domain.kakaoPay.controller;

import jakarta.servlet.http.HttpSession;
import jpabook.jpashop.domain.kakaoPay.dto.KakaoApproveResponse;
import jpabook.jpashop.domain.kakaoPay.dto.KakaoCancelResponse;
import jpabook.jpashop.domain.kakaoPay.dto.KakaoRequsetDTO;
import jpabook.jpashop.domain.kakaoPay.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
@RequiredArgsConstructor
@Log4j2
public class KakaoPayController {
    private final KakaoPayService kakaoPayService;

//    @GetMapping
//    public String home() {
//        return "kakaoPay/kakaoPay";
//    }

//    @ResponseBody
//    @PostMapping("/ready")
//    public KakaoReadyResponse readyToKakaoPay(HttpSession session) {
//
//        KakaoDTO kakaoDTO = (KakaoDTO) session.getAttribute("kakaoOrder");
//        log.info("[Kakao]" + kakaoDTO);
//        return kakaoPayService.kakaoPayReady(kakaoDTO);
//    }

    @ResponseBody
    @GetMapping("/success")
    public ResponseEntity afterPayRequest(@RequestParam("pg_token") String pgToken, HttpSession session) {

        Long orderId = (Long) session.getAttribute("orderId");
        Long memberId = (Long) session.getAttribute("memberId");

        KakaoApproveResponse kakaoApprove = kakaoPayService.approveResponse(pgToken, orderId,memberId);

        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/refund")
    public ResponseEntity refund() {

        KakaoCancelResponse kakaoCancelResponse = kakaoPayService.kakaoCancel();

        return new ResponseEntity<>(kakaoCancelResponse, HttpStatus.OK);
    }
}
