package jpabook.jpashop.domain.repository;

import jpabook.jpashop.domain.entity.Item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAll();
    Page<Item> findByIdBetween(Long from, Long to, Pageable pageable);
}
