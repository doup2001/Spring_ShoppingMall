package jpabook.jpashop;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.entity.*;
import jpabook.jpashop.domain.entity.Item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;


@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;
    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();

        for (int i = 1; i < 300; i++) {
            initService.itemInit(i);
        }
    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void itemInit(int i) {
            Book book = new Book();
                book.setName("Book "+i);
                book.setStockQuantity(i);
                book.setPrice(i);
                em.persist(book);
            }

        public void dbInit1() {
            Member member = createMember("userA", "1234",  "서울", "1", "1111"); em.persist(member);
            Book book1 = createBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);
            Book book2 = createBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);
            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
            Order order = Order.createOrder(member, createDelivery(member),
                    orderItem1, orderItem2);

            em.persist(order);
        }
        public void dbInit2() {
            Member member = createMember("userB", "12345", "진주", "2", "2222"); em.persist(member);
            Book book1 = createBook("SPRING1 BOOK", 20000, 200);
            em.persist(book1);
            Book book2 = createBook("SPRING2 BOOK", 40000, 300);
            em.persist(book2);
            Delivery delivery = createDelivery(member);
            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);
            Order order = Order.createOrder(member, delivery, orderItem1,
                    orderItem2);
            em.persist(order);
        }

        private Member createMember(String username,String password, String city, String street,
                                    String zipcode) {
            Member member = new Member();
            member.setUsername(username);
            member.setPassword(password);
            member.setAddress(new Address(city, street, zipcode));
            member.setRole(Role.ROLE_ADMIN);
            return member;
        }
        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }
        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }
    }
}