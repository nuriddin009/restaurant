package com.example.restaurant.service;

import com.example.restaurant.dto.request.OrderItemRequest;
import com.example.restaurant.dto.request.OrderRequest;
import com.example.restaurant.dto.request.TableRequest;
import com.example.restaurant.dto.response.ApiResponse;
import com.example.restaurant.dto.response.BaseResponse;
import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.OrderItem;
import com.example.restaurant.entity.RestaurantTable;
import com.example.restaurant.entity.enums.AvailabilityStatus;
import com.example.restaurant.entity.enums.OrderStatus;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.repository.OrderItemRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantTableService {

    private final RestaurantTableRepository tableRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MenuRepository menuRepository;


    public ApiResponse takeTable(@Valid OrderRequest request) {
        RestaurantTable restaurantTable = tableRepository.getReferenceById(request.getTableId());
        restaurantTable.setAvailabilityStatus(AvailabilityStatus.valueOf(request.getStatus()));
        RestaurantTable save = tableRepository.save(restaurantTable);

        List<OrderItem> orderItemList = orderItemRepository.saveAll(request.getOrderItems().stream()
                .map(orderItemRequest -> new OrderItem(
                        menuRepository.getReferenceById(orderItemRequest.getMenuId()),
                        orderItemRequest.getQuantity()
                )).collect(Collectors.toList()));

        orderRepository.save(new Order(save, OrderStatus.ACCEPTED, orderItemList));
        return new ApiResponse(true, "The table was reserved");
    }


    public ApiResponse registerNewTable(TableRequest request) {
        RestaurantTable save = tableRepository.save(new RestaurantTable(
                request.getTableNumber(),
                request.getCapacity(),
                AvailabilityStatus.VACANT
        ));
        return new ApiResponse(true, save, "Table created");
    }

    public BaseResponse<?> changeStatus(UUID tableId, String tableStatus, BaseResponse<?> response) {
        try {
            RestaurantTable restaurantTable = tableRepository.getReferenceById(tableId);
            restaurantTable.setAvailabilityStatus(AvailabilityStatus.valueOf(tableStatus));
            tableRepository.save(restaurantTable);
            response.setMessage("Table status updated");
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setError(true);
        }
        return response;
    }
}
