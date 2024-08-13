package jpabook.jpashop.domain.controller;

import jpabook.jpashop.domain.entity.Item.Book;
import jpabook.jpashop.domain.entity.Item.Item;
import jpabook.jpashop.domain.dto.ItemFormDto;
import jpabook.jpashop.domain.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/new")
    public String createItem(Model model) {
        model.addAttribute("form", new ItemFormDto());
        return "/items/createItemForm";
    }

    @PostMapping("/new")
    public String postItem(@ModelAttribute ItemFormDto form) {
        Book item = new Book();
        item.setName(form.getName());
        item.setPrice(form.getPrice());
        item.setStockQuantity(form.getStockQuantity());
        item.setAuthor(form.getAuthor());
        item.setIsbn(form.getIsbn());

        itemService.save(item);
        return "redirect:/items";
    }

    @GetMapping
    public String listItem(Model model) {
        List<Item> items = itemService.findByAll();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/{itemId}/edit")
    public String Edit(@PathVariable Long itemId, Model model) {

        Book item = (Book) itemService.findByone(itemId);

        ItemFormDto form = new ItemFormDto();

        form.setId(item.getId());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setName(item.getName());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }
}
