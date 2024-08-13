package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.controller.OrderSearch;
import jpabook.jpashop.domain.entity.*;
import jpabook.jpashop.domain.entity.Item.Item;
import jpabook.jpashop.domain.repository.ItemRepository;
import jpabook.jpashop.domain.repository.MemberRepository;
import jpabook.jpashop.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
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
        Member member = memberRepository.findByOne(memberId);
        Item item = itemRepository.findByOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setDeliveryStatus(DeliveryStatus.READY);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.order(order);
        return order.getId();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id);
    }

    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id);
        order.cancel();
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
