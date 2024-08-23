package jpabook.jpashop.domain.service;

import jakarta.transaction.Transactional;
import jpabook.jpashop.domain.dto.OAuth2UserResponse;
import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.domain.entity.Role;
import jpabook.jpashop.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(Member member) {

        // 회원 저장
        member.setRole(Role.ROLE_USER);
        return memberRepository.save(member).getId();
    }

    public Member findByName(String username) {
        return memberRepository.findByName(username);
    }

    public Member findById(Long id) {
        // 회원 ID로 조회 (Optional 사용)
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + id));
    }

    public List<Member> findAll() {
        // 모든 회원 조회
        return memberRepository.findAll();
    }

    public void delete(Long id) {
        Member member = findById(id);
        memberRepository.delete(member);
    }


}
