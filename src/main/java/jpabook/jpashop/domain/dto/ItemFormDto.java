package jpabook.jpashop.domain.dto;

import lombok.Data;

@Data
public class ItemFormDto {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

}
