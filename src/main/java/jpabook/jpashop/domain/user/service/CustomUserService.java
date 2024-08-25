package jpabook.jpashop.domain.user.service;

import jpabook.jpashop.domain.repository.MemberRepository;
import jpabook.jpashop.domain.user.CustomUser;
import jpabook.jpashop.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserService implements UserDetailsService {

    //    private final MemberService memberService;
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        Member member = memberService.findByName(username);
        Member member = memberRepository.findByUsername(username);

        log.info(member.getUsername());

        if(member != null) {
            return new CustomUser(member, "ROLE_USER");
        } else if (member == null) {
            log.warn("User not found: {}", username);
            throw new UsernameNotFoundException("User not found: " + username);
        }
        log.info("User found: {}", member.getUsername());
        return null;
    }
}
