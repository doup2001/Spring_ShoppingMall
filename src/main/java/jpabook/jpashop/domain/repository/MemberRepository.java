package jpabook.jpashop.domain.repository;

import jpabook.jpashop.domain.entity.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUsername(String name);

    @Override
    List<Member> findAll();
}
