package jpabook.jpashop.domain.repository;

import jpabook.jpashop.domain.entity.Item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    // 기본적인 CRUD 메소드가 자동으로 제공됩니다.

    // 추가적인 사용자 정의 메소드가 필요하다면 여기에 정의할 수 있습니다.
}
