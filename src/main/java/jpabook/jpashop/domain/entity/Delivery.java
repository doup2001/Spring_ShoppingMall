package jpabook.jpashop.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private long id;

    @Embedded
    private Address address;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;

    @Enumerated(EnumType.ORDINAL)
    private DeliveryStatus deliveryStatus;


}
