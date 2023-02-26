package com.example.restaurant.entity;


import com.example.restaurant.entity.template.AbstractEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends AbstractEntity {

    private String name;

}
