package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Item.Book;
import jpabook.jpashop.domain.Item.Item;
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
        return itemRepository.save(item);
    }

    public Item findByone(Long id) {
        return itemRepository.findByOne(id);
    }

    public List<Item> findByAll() {
        return itemRepository.findByAll();
    }

    //수정 로직

    public Item update(Long id, ItemFormDto updateItem) {
        Book item = (Book) itemRepository.findByOne(id);

        item.setName(updateItem.getName());
        item.setPrice(updateItem.getPrice());
        item.setStockQuantity(updateItem.getStockQuantity());
        item.setIsbn(updateItem.getIsbn());
        item.setAuthor(updateItem.getAuthor());

        return item;
    }

}
