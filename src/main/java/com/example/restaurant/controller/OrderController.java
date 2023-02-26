package com.example.restaurant.controller;

import com.example.restaurant.dto.request.OrderRequest;
import com.example.restaurant.dto.response.ApiResponse;
import com.example.restaurant.dto.response.BaseResponse;
import com.example.restaurant.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService service;


    @PreAuthorize("hasAnyRole('ROLE_WAITER','ROLE_ADMIN')")
    @PostMapping
    public HttpEntity<BaseResponse<ApiResponse>> makeOrder(@RequestBody @Valid OrderRequest request) {
        BaseResponse<ApiResponse> response = new BaseResponse<>();
        response = service.makeOrder(request, response);
        HttpStatus status = response.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }

    @PreAuthorize("hasAnyRole('ROLE_WAITER','ROLE_ADMIN')")
    @PatchMapping("/change_status")
    public ResponseEntity<ApiResponse> changeOrderStatus(
            @RequestParam UUID orderId,
            @RequestParam String status) {
        return ResponseEntity.ok(service.changeOrderStatus(orderId,status));
    }


}
