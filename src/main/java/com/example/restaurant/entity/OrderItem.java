package com.example.restaurant.entity;

import com.example.restaurant.entity.template.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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


}
