package jpabook.jpashop.domain.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberFormDto {

    @NotEmpty(message = "이름을 입력해주세요")
    private String name;

    private String city;
    private String street;
    private String zipcode;

}
