package jpabook.jpashop.domain.controller;

import jpabook.jpashop.domain.entity.Item.Book;
import jpabook.jpashop.domain.entity.Item.Item;
import jpabook.jpashop.domain.dto.ItemFormDto;
import jpabook.jpashop.domain.service.ItemService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/items")
@RequiredArgsConstructor
@Slf4j
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
        return "redirect:/items/0";
    }

//    @GetMapping
//    public String listItem(Model model) {
//        Page<Item> items = itemService.findAll();
//        model.addAttribute("items", items);
//        return "items/itemList"; // 템플릿 경로 수정
//    }

    @GetMapping("/{page}")
    public String listItem(@PathVariable int page, Model model) {
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(page, 10, sort);
        
        Page<Item> items = itemService.findAll(pageable);

        model.addAttribute("items", items);
        model.addAttribute("currentPage", page);

        System.out.println("items.getNumber() = " + items.getNumber());
        System.out.println("items.hasNext() = " + items.hasNext());
        System.out.println("items.getTotalElements() = " + items.getTotalElements());
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
