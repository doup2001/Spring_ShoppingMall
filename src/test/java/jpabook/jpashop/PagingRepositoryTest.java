package jpabook.jpashop;

import jakarta.annotation.PostConstruct;
import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.domain.entity.Role;
import jpabook.jpashop.domain.repository.MemberRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PagingRepositoryTest {

    @Autowired private MemberRepository memberRepository;

    @Before
    public void init() {
        for (int i = 0; i < 20; i++) {
            Member member = new Member();
            member.setUsername("user" + i);
            member.setRole(Role.ROLE_USER);
            memberRepository.save(member);
        }
    }

    @Test
    public void testPageDefault() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Member> result = memberRepository.findAll(pageable);
        System.out.println("result = " + result);
        System.out.println(" ============" );

        System.out.println("result.getTotalPages() = " + result.getTotalPages()); // 총 페이지
        System.out.println("result.getTotalElements() = " + result.getTotalElements()); // 전체 개수
        System.out.println("result.getNumber() = " + result.getNumber()); //현재 페이지 번호
        System.out.println("result.getSize() = " + result.getSize()); // 페이지당 데이터 개수
        System.out.println("result.hasNext() = " + result.hasNext()); // 다음 페이지
        System.out.println("result.isFirst() = " + result.isFirst()); //시작 페이지 여ㅡ

    }

    @Test
    public void test_sort() {
        Sort sortById = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(0, 10, sortById);
        Page<Member> result = memberRepository.findAll(pageable);

        result.get().forEach(member -> {
            System.out.println("member = " + member.getId());
            System.out.println("member.getUsername() = " + member.getUsername());
        });
    }

}
