package com.example.restaurant.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class OrderRequest {

    @NotNull
    private UUID tableId;

    private String status;

    private List<OrderItemRequest> orderItems;

}
