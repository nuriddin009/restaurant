package com.example.restaurant.controller;

import com.example.restaurant.dto.request.CategoryDto;
import com.example.restaurant.dto.response.ApiResponse;
import com.example.restaurant.dto.response.BaseResponse;
import com.example.restaurant.entity.Category;
import com.example.restaurant.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryService service;


    @GetMapping
    public ResponseEntity<ApiResponse> getCategories() {
        return ResponseEntity.ok(service.getCategories());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<BaseResponse<ApiResponse>> createCategory(@RequestBody @Valid CategoryDto request) {
        BaseResponse<ApiResponse> response = new BaseResponse<>();
        response = service.createCategory(request, response);
        HttpStatus status = response.getError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }

}
