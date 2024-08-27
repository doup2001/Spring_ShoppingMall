package jpabook.jpashop.domain.service;

import jakarta.transaction.Transactional;
import jpabook.jpashop.domain.dto.JoinDto;
import jpabook.jpashop.domain.entity.Address;
import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.domain.entity.Role;
import jpabook.jpashop.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long join(JoinDto joinDto) {
        Member member = new Member();
        member.setUsername(joinDto.getUsername());
        member.setAddress(new Address(joinDto.getCity(), joinDto.getStreet(), joinDto.getZipcode()));
        member.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));
//        member.setPassword(joinDto.getPassword());
        member.setEmail(joinDto.getEmail());
        member.setRole(Role.ROLE_USER);

        return memberRepository.save(member).getId();
    }

    public Member findByUsername(String username) {
        // 사용자 이름으로 회원 조회
        return memberRepository.findByUsername(username);
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
