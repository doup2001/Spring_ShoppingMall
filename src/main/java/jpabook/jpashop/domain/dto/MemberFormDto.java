package jpabook.jpashop.domain.dto;

import lombok.Data;

@Data
public class MemberFormDto {


    private String name;

    private String city;
    private String street;
    private String zipcode;

}
