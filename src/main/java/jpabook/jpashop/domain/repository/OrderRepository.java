package jpabook.jpashop.domain.repository;

import jpabook.jpashop.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    // 기본 CRUD 메소드와 동적 쿼리 메소드를 모두 제공받습니다.
}
