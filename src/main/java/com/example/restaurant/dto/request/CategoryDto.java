package com.example.restaurant.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDto {

    @NotNull
    private String name;

}
