package com.example.restaurant.controller;

import com.example.restaurant.dto.request.OrderRequest;
import com.example.restaurant.dto.request.TableRequest;
import com.example.restaurant.dto.response.ApiResponse;
import com.example.restaurant.service.RestaurantTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/place")
public class RestaurantTableController {

    private final RestaurantTableService service;


    @PreAuthorize("hasAnyRole('ROLE_WAITER','ROLE_ADMIN')")
    @PostMapping("/takeTable")
    public HttpEntity<ApiResponse> takeTable(@RequestBody @Valid OrderRequest request) {
        return ResponseEntity.ok(service.takeTable(request));
    }


    @PreAuthorize("hasAnyRole('ROLE_WAITER','ROLE_ADMIN')")
    @PostMapping("/register")
    public HttpEntity<ApiResponse> registerNewTable(@RequestBody TableRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.registerNewTable(request));
    }


}
