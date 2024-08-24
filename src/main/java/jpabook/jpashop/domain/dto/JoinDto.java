package jpabook.jpashop.domain.dto;

import lombok.Data;

@Data
public class JoinDto {

    private String user_id;
    private String name;
    private String email;
    private String password;
    private String city;
    private String street;
    private String zipcode;


}
