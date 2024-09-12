package jpabook.jpashop.domain.kakaoPay.service;

import jakarta.transaction.Transactional;
import jpabook.jpashop.domain.kakaoPay.dto.KakaoApproveResponse;
import jpabook.jpashop.domain.kakaoPay.dto.KakaoCancelResponse;
import jpabook.jpashop.domain.kakaoPay.dto.KakaoReadyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional
public class KakaoPayService {

    static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드
    static final String admin_Key = "04892e84443149e77874b7e83acbbef7"; // 공개 조심! 본인 애플리케이션의 어드민 키를 넣어주세요
    private KakaoReadyResponse kakaoReady;

    public KakaoReadyResponse kakaoPayReady() {

        // 카카오페이 요청 양식
        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", "partner_order_id");
        parameters.add("partner_user_id", "partner_user_id");
        parameters.add("item_name", "생수 1L");
        parameters.add("quantity", 10);
        parameters.add("total_amount", 6000);
        parameters.add("vat_amount", 0);
        parameters.add("tax_free_amount", 0);
        parameters.add("approval_url", "http://localhost:8080/payment/success"); // 성공 시 redirect url
        parameters.add("cancel_url", "http://localhost:8080/payment/cancel"); // 취소 시 redirect url
        parameters.add("fail_url", "http://localhost:8080/payment/fail"); // 실패 시 redirect url

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        kakaoReady = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/ready",
                requestEntity,
                KakaoReadyResponse.class);

        return kakaoReady;
    }


    /**
     * 카카오 요구 헤더값
     */
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth = "KakaoAK " + admin_Key;

        httpHeaders.set("Authorization", auth);
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpHeaders;
    }
}