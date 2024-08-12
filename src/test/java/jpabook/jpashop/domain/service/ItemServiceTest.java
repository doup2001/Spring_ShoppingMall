package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.entity.Item.Book;
import jpabook.jpashop.domain.entity.Item.Item;
import jpabook.jpashop.domain.dto.ItemFormDto;
import jpabook.jpashop.domain.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(value = false)
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
    public void 아이템_수정() throws Exception{
        //given
        Book item = new Book();
        item.setName("JPA");
        item.setPrice(15000);
        item.setStockQuantity(10);
        item.setAuthor("김영한");
        item.setIsbn("1234");
        Long ItemId = itemService.save(item);

        //when
        ItemFormDto updateDto = new ItemFormDto();
        String new_name = "이도연";
        updateDto.setAuthor(new_name);

        Book updateItem = (Book) itemService.update(ItemId, updateDto);

        //then
        assertThat(updateItem.getAuthor()).isEqualTo(new_name);
    }
}