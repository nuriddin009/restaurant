package com.example.restaurant.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class OrderItemRequest {

    @NotNull
    private UUID menuId;

    @NotNull
    private Integer quantity;

}
