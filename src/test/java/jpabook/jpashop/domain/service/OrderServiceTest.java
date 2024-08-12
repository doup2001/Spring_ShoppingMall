package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.entity.*;
import jpabook.jpashop.domain.entity.Item.Book;
import jpabook.jpashop.domain.entity.Item.Item;
import jpabook.jpashop.domain.entity.Item.NotEnoughStockException;
import jpabook.jpashop.domain.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired private MemberService memberService;
    @Autowired private ItemService itemService;
    @Autowired private OrderService orderService;
    @Autowired private OrderRepository orderRepository;

    @Test
    public void 저장 () throws Exception{
        //given
        Member member = createMember();
        memberService.join(member);

        Item item = createItem();
        itemService.save(item);

        //when
        Long id = orderService.save(member.getId(), item.getId(), 3);
        Order getOrder = orderRepository.findById(id);

        //then
        Assertions.assertThat(getOrder.getId()).isEqualTo(id);
        assertEquals(OrderStatus.ORDER, getOrder.getOrderStatus(), "상품 주문시 상태는 ORDER");
    }

    
    @Test
    public void 주문_수량() throws Exception{
        //given
        Member member = createMember();
        memberService.join(member);

        int stock = 100;

        Item item = new Book();
        item.setName("JPA");
        item.setPrice(10000);
        item.setStockQuantity(stock);
        itemService.save(item);

        //when
        Long id = orderService.save(member.getId(), item.getId(), 3);
        Order getOrder = orderService.findById(id);

        //then
        assertEquals(97, item.getStockQuantity());
    }
    
    @Test
    public void 주문_취소() throws Exception{
        //given
        Member member = createMember();
        memberService.join(member);

        int stock = 100;

        Item item = new Book();
        item.setName("JPA");
        item.setPrice(10000);
        item.setStockQuantity(stock);
        itemService.save(item);
        Long id = orderService.save(member.getId(), item.getId(), 3);
        Order getOrder = orderService.findById(id);

        //when
        orderService.cancelOrder(getOrder.getId());
        
        //then
        assertEquals(100, item.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class) public void 상품주문_재고수량초과() throws Exception {
        //Given
        Member member = createMember();
        memberService.join(member);

        Item item = createItem();
        itemService.save(item);

        int orderCount = 11; //재고보다 많은 수량 //When
        orderService.save(member.getId(), item.getId(), orderCount);

        //Then
        fail("재고 수량 부족 예외가 발생해야 한다."); }

    private static Member createMember() {
        Member member = new Member();
        member.setName("lee");
        member.setAddress(new Address("성남시", "장미로 55", "126-602"));
        return member;
    }

    private static Item createItem() {
        Item item = new Book();
        item.setName("JPA");
        item.setPrice(10000);
        item.setStockQuantity(3);
        return item;
    }
}