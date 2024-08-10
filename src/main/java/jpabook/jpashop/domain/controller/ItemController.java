package jpabook.jpashop.domain.controller;

import jpabook.jpashop.domain.dto.ItemFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("items")
public class ItemController {

    @GetMapping("/new")
    public String createItem(Model model) {
        model.addAttribute("form", new ItemFormDto());
        return "/items/createItemForm";
    }

}
