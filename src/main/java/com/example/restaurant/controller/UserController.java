package com.example.restaurant.controller;

import com.example.restaurant.dto.request.RegisterRequest;
import com.example.restaurant.dto.response.ApiResponse;
import com.example.restaurant.dto.response.BaseResponse;
import com.example.restaurant.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService service;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse> getMe() {
        return ResponseEntity.ok(service.getMe());
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/register_waiter")
    public ResponseEntity<BaseResponse<ApiResponse>> registerWaiter(@Valid @RequestBody RegisterRequest request) {
        BaseResponse<ApiResponse> response = new BaseResponse<>();
        response = service.registerWaiter(request, response);
        HttpStatus status = response.getError() ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }

}
