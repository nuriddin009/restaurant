package com.example.restaurant.service;

import com.example.restaurant.dto.request.CategoryDto;
import com.example.restaurant.dto.response.ApiResponse;
import com.example.restaurant.dto.response.BaseResponse;
import com.example.restaurant.entity.Category;
import com.example.restaurant.projection.CategoryProjection;
import com.example.restaurant.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public ApiResponse getCategories() {
        return new ApiResponse(true, categoryRepository.findAllByOrderByCreatedAt());
    }

    public BaseResponse<ApiResponse> createCategory(CategoryDto request, BaseResponse<ApiResponse> response) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Category category = categoryRepository.save(new Category(request.getName()));
            apiResponse = new ApiResponse(true, (CategoryProjection) category);
        } catch (Exception e) {
            response.setError(true);
            response.setMessage(e.getMessage());
        }
        response.setResponseData(apiResponse);
        return response;
    }
}
