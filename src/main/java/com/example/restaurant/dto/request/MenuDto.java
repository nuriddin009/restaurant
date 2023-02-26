package com.example.restaurant.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class MenuDto {

    @NotNull
    private UUID categoryId;

    @NotNull
    private String name;

    @NotNull
    private Long price;

    @NotNull
    private UUID attachmentId;

}
