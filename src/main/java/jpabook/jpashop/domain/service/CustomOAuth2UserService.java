package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.dto.CustomOAuth2User;
import jpabook.jpashop.domain.dto.NaverResponse;
import jpabook.jpashop.domain.dto.OAuth2UserResponse;
import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.domain.entity.Role;
import jpabook.jpashop.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import static jpabook.jpashop.domain.entity.Role.ROLE_USER;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberService memberService;
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException{

        OAuth2User oAuth2User = super.loadUser(request);
        log.info(oAuth2User.getAttributes().toString());

        String registrationId = request.getClientRegistration().getRegistrationId();

        OAuth2UserResponse oAuth2UserResponse = null;

        if (registrationId.equals("naver")) {
             oAuth2UserResponse = new NaverResponse(oAuth2User.getAttributes());
        }

        String username = oAuth2UserResponse.getProvider() + " " + oAuth2UserResponse.getName();
        Member existsmember = memberService.findByName(username);
        if (existsmember == null) {
            Member member = new Member();
            member.setName(username);
            member.setEmail(oAuth2UserResponse.getEmail());
            member.setRole(ROLE_USER);

            memberService.join(member);
        } else {
            existsmember.setEmail(oAuth2UserResponse.getEmail());
            memberService.join(existsmember);
        }

        String role = "ROLE_USER";
        return new CustomOAuth2User(oAuth2UserResponse, role);

    }



}
