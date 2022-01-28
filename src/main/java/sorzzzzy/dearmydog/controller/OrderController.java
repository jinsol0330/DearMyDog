package sorzzzzy.dearmydog.controller;

import sorzzzzy.dearmydog.domain.Member;
import sorzzzzy.dearmydog.domain.Order;
import sorzzzzy.dearmydog.domain.Item.Item;
import sorzzzzy.dearmydog.repository.OrderSearch;
import sorzzzzy.dearmydog.service.ItemService;
import sorzzzzy.dearmydog.service.MemberService;
import sorzzzzy.dearmydog.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        // 내가 가진 모든 멤버들과 아이템들을 다 가져옴
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();
        // 가져온 후 모델에 담아서 폼으로 넘김
        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {
        // 파라미터로 주문 생성
        orderService.order(memberId, itemId, count);
        // 주문 내역 목록으로 리다이렉트
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
