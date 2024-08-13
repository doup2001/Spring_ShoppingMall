package jpabook.jpashop.domain.controller;

import jpabook.jpashop.domain.entity.OrderStatus;
import lombok.Data;

@Data
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;

}


