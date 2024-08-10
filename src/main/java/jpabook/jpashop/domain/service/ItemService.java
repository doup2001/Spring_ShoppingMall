package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Item.Item;
import jpabook.jpashop.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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

}
