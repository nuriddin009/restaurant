package com.example.restaurant.controller;

import com.example.restaurant.dto.request.OrderRequest;
import com.example.restaurant.dto.request.TableRequest;
import com.example.restaurant.dto.response.ApiResponse;
import com.example.restaurant.dto.response.BaseResponse;
import com.example.restaurant.service.RestaurantTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

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

    @PreAuthorize("hasAnyRole('ROLE_WAITER','ROLE_ADMIN')")
    @PatchMapping("/change_status")
    public ResponseEntity<BaseResponse<?>> changeStatus(
            @RequestParam UUID tableId,
            @RequestParam String tableStatus
    ) {
        BaseResponse<?> response = new BaseResponse<>();
        response = service.changeStatus(tableId, tableStatus, response);
        HttpStatus status = response.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }

}
