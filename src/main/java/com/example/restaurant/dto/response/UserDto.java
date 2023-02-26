package com.example.restaurant.dto.response;

import com.example.restaurant.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {

    private String phoneNumber;


    private List<Role> roles;


}
