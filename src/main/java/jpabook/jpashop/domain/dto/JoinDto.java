package jpabook.jpashop.domain.dto;

import lombok.Data;

@Data
public class JoinDto {

    private String username;
    private String email;
    private String password;
    private String city;
    private String street;
    private String zipcode;


}
