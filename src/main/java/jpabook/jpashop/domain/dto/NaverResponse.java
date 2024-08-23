package jpabook.jpashop.domain.dto;

import java.util.Map;
import java.util.Optional;

public class NaverResponse implements OAuth2UserResponse {

    private final Map<String, Object> attribute;

    @SuppressWarnings("unchecked")
    public NaverResponse(Map<String, Object> attributes) {
        // `response`가 Map<String, Object> 형식인지 확인 후 안전하게 캐스팅
        Object response = attributes.get("response");
        if (response instanceof Map) {
            this.attribute = (Map<String, Object>) response;
        } else {
            throw new IllegalArgumentException("Invalid response format");
        }
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        return Optional.ofNullable(attribute.get("id"))
                .map(Object::toString)
                .orElse("Unknown");
    }

    @Override
    public String getEmail() {
        return Optional.ofNullable(attribute.get("email"))
                .map(Object::toString)
                .orElse("No email provided");
    }

    @Override
    public String getName() {
        return Optional.ofNullable(attribute.get("name"))
                .map(Object::toString)
                .orElse("No name provided");
    }
}
