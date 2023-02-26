package com.example.restaurant.entity;


import com.example.restaurant.entity.enums.AvailabilityStatus;
import com.example.restaurant.entity.template.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class RestaurantTable extends AbstractEntity {


    private Integer tableNumber;
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability_status")
    private AvailabilityStatus availabilityStatus = AvailabilityStatus.VACANT;


}
