package jpabook.jpashop.domain.user.dto;

public interface OAuth2UserResponse {

    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();

}
