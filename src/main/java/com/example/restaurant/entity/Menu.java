package com.example.restaurant.entity;

import com.example.restaurant.entity.template.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Menu extends AbstractEntity {

    @Column(length = 50)
    private String productName;

    private Integer price;


}
