package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.entity.Item.Book;
import jpabook.jpashop.domain.entity.Item.Item;
import jpabook.jpashop.domain.dto.ItemFormDto;
import jpabook.jpashop.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public Long save(Item item) {
        return itemRepository.save(item).getId();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + id));
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    // 수정 로직
    public Item update(Long id, ItemFormDto updateItem) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + id));

        item.setName(updateItem.getName());
        item.setPrice(updateItem.getPrice());
        item.setStockQuantity(updateItem.getStockQuantity());
        // `Item` 클래스에 `ISBN`과 `Author`를 설정할 수 있는 메소드가 없다면, 이를 처리할 하위 클래스를 사용해야 함
        if (item instanceof Book) {
            Book book = (Book) item;
            book.setIsbn(updateItem.getIsbn());
            book.setAuthor(updateItem.getAuthor());
        }

        // 엔티티 매니저에 의해 자동으로 업데이트됨 (영속성 컨텍스트에 의해 트랜잭션 종료 시 자동 저장)
        return item;
    }
}
