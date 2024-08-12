package jpabook.jpashop.domain.service;

import jakarta.transaction.Transactional;
import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(Member member) {
        Long memberId = memberRepository.save(member);
        return memberId;
    }

    public Member findByone(Long id) {
        Member member = memberRepository.findByOne(id);
        return member;
    }

    public List<Member> findByALl() {
        return memberRepository.findByAll();
    }

}
