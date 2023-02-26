package com.example.restaurant.service;

import com.example.restaurant.dto.request.MenuDto;
import com.example.restaurant.dto.response.ApiResponse;
import com.example.restaurant.dto.response.BaseResponse;
import com.example.restaurant.entity.Menu;
import com.example.restaurant.projection.MenuProjection;
import com.example.restaurant.repository.AttachmentRepository;
import com.example.restaurant.repository.CategoryRepository;
import com.example.restaurant.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenuService {


    private final MenuRepository menuRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;


    public ApiResponse getMenu(UUID categoryId, String name, String sortByPrice) {
        Page<MenuProjection> menuBy = menuRepository.getMenuBy(categoryId, name, PageRequest.of(0, 100000, sortByPrice.equals("asc") ?
                Sort.by("price").ascending() : Sort.by("price").descending()));
        return new ApiResponse(true, menuBy);
    }

    public BaseResponse<ApiResponse> createMenu(BaseResponse<ApiResponse> response, @Valid MenuDto menuDto) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            Menu menu = menuRepository.save(new Menu(
                    menuDto.getName(),
                    menuDto.getPrice(),
                    categoryRepository.getReferenceById(menuDto.getCategoryId()),
                    attachmentRepository.getReferenceById(menuDto.getAttachmentId())
            ));
            apiResponse = new ApiResponse(true, menu, "Menu created");
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        response.setResponseData(apiResponse);
        return response;
    }
}
