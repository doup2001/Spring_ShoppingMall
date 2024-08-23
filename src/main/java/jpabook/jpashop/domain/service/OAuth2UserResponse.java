package jpabook.jpashop.domain.service;

public interface OAuth2UserResponse {

    String getProvider();

    String getProviderId();

    String getEmail();

    String getUsername();

}
