package jpabook.jpashop.domain.entity.Item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {


    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //// 비즈니스 로직

    public void removeOrder(int quantity) {
        this.stockQuantity += quantity;
    }

    public void addOrder(int quantity) {
        int res = stockQuantity - quantity;

        if (res < 0)
            throw new NotEnoughStockException(new Throwable());
        else
            this.stockQuantity = res;
    }
}
