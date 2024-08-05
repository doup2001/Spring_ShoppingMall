package jpabook.jpashop.Test_domain;

import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired private MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void save() {

        //given
        Member memberA = new Member();
        memberA.setName("member");
        Long saveId = memberRepository.save(memberA);

        //when
        Member findMember = memberRepository.find(saveId);

        //then
        assertThat(findMember.getId()).isEqualTo(memberA.getId());
        assertThat(findMember.getName()).isEqualTo(memberA.getName());
        assertThat(findMember).isEqualTo(memberA);

    }


}