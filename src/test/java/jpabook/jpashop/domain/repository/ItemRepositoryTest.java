package jpabook.jpashop.domain.repository;

import jpabook.jpashop.domain.entity.Item.Book;
import jpabook.jpashop.domain.entity.Item.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemRepositoryTest {

    @Autowired private ItemRepository itemRepository;

    @Before
    public void testDummies() {
        IntStream.rangeClosed(1,100).forEach(i->{
            Item item = new Book();
            item.setName("Book " + i);
            itemRepository.save(item);
        });
    }
    @Test
    public void testQueryWithPageable() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Item> result = itemRepository.findByIdBetween(10L, 40L, pageable);
        result.get().forEach(
                item -> System.out.println("item.getId() = " + item.getId())
        );
    }

}