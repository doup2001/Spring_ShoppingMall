package jpabook.jpashop.domain.controller;


import jpabook.jpashop.domain.entity.Item.Item;
import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.domain.entity.Order;
import jpabook.jpashop.domain.repository.MemberRepository;
import jpabook.jpashop.domain.service.ItemService;
import jpabook.jpashop.domain.service.MemberService;
import jpabook.jpashop.domain.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
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
    public String order(@RequestParam Long MemberId, @RequestParam Long ItemId, @RequestParam int count) {
        Long orderId = orderService.save(MemberId, ItemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String list(Model model) {
        orderService.findAll();
        model.addAttribute("orders", orders);

        return "order/ordeList";
    }
}
