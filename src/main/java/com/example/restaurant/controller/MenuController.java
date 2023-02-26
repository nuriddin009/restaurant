package com.example.restaurant.controller;

import com.example.restaurant.dto.request.MenuDto;
import com.example.restaurant.dto.response.ApiResponse;
import com.example.restaurant.dto.response.BaseResponse;
import com.example.restaurant.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/menu")
public class MenuController {


    private final MenuService service;

    @GetMapping
    public ResponseEntity<ApiResponse> getMenu(
            @RequestParam(required = false, defaultValue = "") UUID categoryId,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false) String sortByPrice
    ) {
        return ResponseEntity.ok(service.getMenu(categoryId, name, sortByPrice));
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<BaseResponse<ApiResponse>> createMenu(@Valid @RequestBody MenuDto menuDto) {
        BaseResponse<ApiResponse> response = new BaseResponse<>();
        response = service.createMenu(response,menuDto);
        HttpStatus status = response.getError() ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.OK;
        return new ResponseEntity<>(response, status);
    }


}
