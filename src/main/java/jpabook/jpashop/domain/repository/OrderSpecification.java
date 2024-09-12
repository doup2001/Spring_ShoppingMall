package jpabook.jpashop.domain.repository;

import jpabook.jpashop.domain.controller.OrderSearch;
import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.domain.entity.Order;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

public class OrderSpecification {

    public static Specification<Order> search(OrderSearch orderSearch) {
        return (root, query, criteriaBuilder) -> {
            // Order와 Member를 조인
            Join<Order, Member> memberJoin = root.join("member", JoinType.LEFT); // 조인 타입을 LEFT로 변경 가능

            Predicate predicate = criteriaBuilder.conjunction();

            // 주문 상태 검색
            if (orderSearch.getOrderStatus() != null) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("orderStatus"), orderSearch.getOrderStatus()));
            }

            // 회원 이름 검색
            if (orderSearch.getMemberName() != null && !orderSearch.getMemberName().isEmpty()) {
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(memberJoin.get("name")), "%" + orderSearch.getMemberName().toLowerCase() + "%"));
            }

            // 쿼리에서 결과 중복 제거 (distinct)
            query.distinct(true);

            return predicate;
        };
    }
}
