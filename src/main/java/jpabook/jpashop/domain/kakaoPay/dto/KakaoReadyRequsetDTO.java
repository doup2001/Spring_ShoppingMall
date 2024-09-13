package jpabook.jpashop.domain.kakaoPay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@Log4j2
public class KakaoReadyRequsetDTO {

    private String cid;
    private Long partner_order_id;
    private Long partner_user_id;
    private String item_name;
    private int quantity;
    private int total_amount;
    private int vat_amount;
    private int tax_free_amount;
    private String approval_url;
    private String cancel_url;
    private String fail_url;

//    @Value("${kakao.success.url}")
//    String successUrl;
//
//    @Value("${kakao.cancel.url}")
//    String cancelUrl;
//
//    @Value("${kakao.fail.url}")
//    String failUrl;

    public MultiValueMap<String, Object> getParametersMap() {
        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", this.cid);
        parameters.add("partner_order_id", this.partner_order_id); //가맹점 주문번호
        parameters.add("partner_user_id", this.partner_user_id); //가맹점 회원 ID
        parameters.add("item_name", this.item_name); //아이템 이름
        parameters.add("quantity", this.quantity); //갯수
        parameters.add("total_amount", this.total_amount); //총 가격
        parameters.add("vat_amount", this.vat_amount); //
        parameters.add("tax_free_amount", this.tax_free_amount);
        parameters.add("approval_url", "eedo.shop:8080/order/success"); // 성공 시 redirect url
        parameters.add("cancel_url", "eedo.shop:8080/order/cancel"); // 취소 시 redirect url
        parameters.add("fail_url", "eedo.shop:8080/order/fail"); // 실패 시 redirect url


        return parameters;
    }
}
