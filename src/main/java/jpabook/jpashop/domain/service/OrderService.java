package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.controller.OrderSearch;
import jpabook.jpashop.domain.entity.*;
import jpabook.jpashop.domain.entity.Item.Item;
import jpabook.jpashop.domain.repository.ItemRepository;
import jpabook.jpashop.domain.repository.MemberRepository;
import jpabook.jpashop.domain.repository.OrderRepository;
import jpabook.jpashop.domain.repository.OrderSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + memberId));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + itemId));

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setDeliveryStatus(DeliveryStatus.READY);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order); // Save the order
        return order.getId();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));
    }

    public void cancelOrder(Long id) {
        Order order = findById(id); // Use findById to handle the exception
        order.cancel(); // Call cancel method on the found order
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        // Use findAll with Specification to get all matching orders without pagination
        return orderRepository.findAll(OrderSpecification.search(orderSearch));
    }
}
