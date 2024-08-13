package jpabook.jpashop.domain.controller;


import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.entity.Item.Item;
import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.domain.entity.Order;
import jpabook.jpashop.domain.service.ItemService;
import jpabook.jpashop.domain.service.MemberService;
import jpabook.jpashop.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final MemberService memberService;
    private final OrderService orderService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String orderForm(Model model) {

        List<Member> members = memberService.findByALl();
        List<Item> items = itemService.findByAll();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam Long memberId, @RequestParam Long itemId, @RequestParam int count, Model model) {

        Long orderId = orderService.order(memberId, itemId, count);
        Order order = orderService.findById(orderId);

        model.addAttribute("order", order);

        return "redirect:/order";
    }

    @GetMapping("/orders")
    public String list(@ModelAttribute OrderSearch orderSearch, Model model) {

        List<Order> orders = orderService.findAll(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    //주문취소 버튼
    @PostMapping("/orders/{orderId}/cancel")
    public String cancel(@PathVariable Long orderId) {

        Order order = orderService.findById(orderId);
        orderService.cancelOrder(order.getId());

        return "redirect:/orders";
    }

    //주문 검색 버튼
    @PostMapping("/orders")
    public String search(@RequestParam String memberName, @RequestParam OrderStatus orderStatus) {
        return "redirect:/orders";
    }

}
