package jpabook.jpashop.domain.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.entity.Item.Item;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ItemRepository {

    private final EntityManager em;

    public Long save(Item item) {
        em.persist(item);
        log.info("[MyLog] Save new Item : " + item);
        return item.getId();
    }

    public Item findByOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findByAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }


}
