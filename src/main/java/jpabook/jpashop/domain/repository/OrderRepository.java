package jpabook.jpashop.domain.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public Long save(Order order) {
        em.persist(order);
        return order.getId();
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findByAll() {
        return em.createQuery("selct o from Order o", Order.class)
                .getResultList();

    }






}
