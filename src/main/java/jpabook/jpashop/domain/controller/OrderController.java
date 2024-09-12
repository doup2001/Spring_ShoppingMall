package jpabook.jpashop.domain.controller;

import jakarta.servlet.http.HttpSession;
import jpabook.jpashop.domain.entity.OrderStatus;
import jpabook.jpashop.domain.entity.Item.Item;
import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.domain.entity.Order;
import jpabook.jpashop.domain.kakaoPay.dto.KakaoDTO;
import jpabook.jpashop.domain.kakaoPay.dto.KakaoReadyResponse;
import jpabook.jpashop.domain.kakaoPay.service.KakaoPayService;
import jpabook.jpashop.domain.service.ItemService;
import jpabook.jpashop.domain.service.MemberService;
import jpabook.jpashop.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class OrderController {

    private final MemberService memberService;
    private final OrderService orderService;
    private final ItemService itemService;
    private final KakaoPayService kakaoPayService;

    @GetMapping("/order")
    public String orderForm(@RequestParam(defaultValue = "0")int page, Model model) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").ascending());

        List<Member> members = memberService.findAll(); // findByAll로 변경
        Page<Item> items = itemService.findAll(pageable); // findByAll로 변경

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    @ResponseBody
    public KakaoReadyResponse order(@RequestParam Long memberId, @RequestParam Long itemId, @RequestParam int count, HttpSession session) {
        Long orderId = orderService.order(memberId, itemId, count);
        Order order = orderService.findById(orderId);


        KakaoDTO kakaoDTO = KakaoDTO.builder()
                .cid("TC0ONETIME")
                .partner_order_id(orderId)
                .partner_user_id(memberId)
                .item_name(itemService.findById(itemId).getName())
                .quantity(count)
                .total_amount(10000*count)
                .vat_amount((int) ((10000*count)*0.1))
                .tax_free_amount(0)
                .build();

        return kakaoPayService.kakaoPayReady(kakaoDTO);
    }

    @GetMapping("/orders")
    public String list(@ModelAttribute OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findAll(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancel(@PathVariable Long orderId) {
        Order order = orderService.findById(orderId);
        orderService.cancelOrder(orderId); // order.getId() 대신 orderId를 직접 사용

        return "redirect:/orders";
    }

    @GetMapping("/orders/search")
    public String search(@RequestParam(required = false) String memberName,
                         @RequestParam(required = false) OrderStatus orderStatus,
                         Model model) {
        OrderSearch orderSearch = new OrderSearch();
        orderSearch.setMemberName(memberName);
        orderSearch.setOrderStatus(orderStatus);

        List<Order> orders = orderService.findAll(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }
}
