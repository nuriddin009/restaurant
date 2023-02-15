package com.example.restaurant.entity;

import com.example.restaurant.entity.template.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
