package jpabook.jpashop.domain.kakaoPay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaoRequsetDTO {

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
        parameters.add("approval_url", "http://localhost:8080/payment/success"); // 성공 시 redirect url
        parameters.add("cancel_url", "http://localhost:8080/payment/cancel"); // 취소 시 redirect url
        parameters.add("fail_url", "http://localhost:8080/payment/fail"); // 실패 시 redirect url

        return parameters;
    }
}