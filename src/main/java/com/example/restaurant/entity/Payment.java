package com.example.restaurant.entity;

import com.example.restaurant.entity.enums.PayType;
import com.example.restaurant.entity.enums.PaymentStatus;
import com.example.restaurant.entity.template.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Payment extends AbstractEntity {

    @JoinColumn(name = "order_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @Column(name = "pay_type")
    @Enumerated(EnumType.STRING)
    private PayType payType;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    private Long amount;

}
