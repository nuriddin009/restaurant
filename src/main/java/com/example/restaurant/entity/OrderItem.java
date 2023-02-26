package com.example.restaurant.entity;

import com.example.restaurant.entity.template.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_item")
public class OrderItem extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    private Order order;

    private Integer quantity;

}
