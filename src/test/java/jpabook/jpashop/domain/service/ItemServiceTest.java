package jpabook.jpashop.domain.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Item.Book;
import jpabook.jpashop.domain.Item.Item;
import jpabook.jpashop.domain.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired private ItemRepository itemRepository;
    @Autowired private ItemService itemService;

    @Test
    public void 저장() throws Exception {
        //given
        Book book = new Book();
        book.setName("JPA");
        book.setPrice(1000);
        book.setStockQuantity(2);
        book.setAuthor("lee");
        book.setIsbn("12B3S");

        //when
        Long itemId = itemService.save(book);

        //then
        assertThat(itemId).isEqualTo(book.getId());
    }

    @Test
    public void 아이템_찾기() throws Exception{
        //given
        Book book = new Book();
        book.setName("JPA");
        book.setPrice(1000);
        book.setStockQuantity(2);
        book.setAuthor("lee");
        book.setIsbn("12B3S");
        Long itemId = itemService.save(book);

        //when
        Item item = itemService.findByone(itemId);
        
        //then
        assertThat(item).isEqualTo(book);
    }

    @Test
    public void 수량체크_증가() throws Exception{
        //given
        
        //when
        
        //then
    }
}