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
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/new")
    public String createItem(Model model) {
        model.addAttribute("form", new ItemFormDto());
        return "items/createItemForm"; // 템플릿 경로 수정
    }

    @PostMapping("/new")
    public String saveItem(@ModelAttribute ItemFormDto form) {
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
        List<Item> items = itemService.findAll();
        model.addAttribute("items", items);
        return "items/itemList"; // 템플릿 경로 수정
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemService.findById(itemId); // Item으로 변경

        if (!(item instanceof Book)) {
            throw new IllegalArgumentException("Item is not of type Book");
        }

        Book book = (Book) item;
        ItemFormDto form = new ItemFormDto();
        form.setId(book.getId());
        form.setPrice(book.getPrice());
        form.setStockQuantity(book.getStockQuantity());
        form.setName(book.getName());
        form.setAuthor(book.getAuthor());
        form.setIsbn(book.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm"; // 템플릿 경로 수정
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute ItemFormDto formDto) {
        itemService.update(itemId, formDto);
        return "redirect:/items";
    }
}
