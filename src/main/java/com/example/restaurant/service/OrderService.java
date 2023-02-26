package com.example.restaurant.service;

import com.example.restaurant.dto.request.OrderRequest;
import com.example.restaurant.dto.response.ApiResponse;
import com.example.restaurant.dto.response.BaseResponse;
import com.example.restaurant.entity.Menu;
import com.example.restaurant.entity.Order;
import com.example.restaurant.entity.OrderItem;
import com.example.restaurant.entity.RestaurantTable;
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

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantTableRepository tableRepository;
    private final MenuRepository menuRepository;
    private final OrderItemRepository orderItemRepository;


    public BaseResponse<ApiResponse> makeOrder(@Valid OrderRequest request, BaseResponse<ApiResponse> response) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            RestaurantTable restaurantTable = tableRepository.getReferenceById(request.getTableId());
            List<OrderItem> orderItems = orderItemRepository.saveAll(request.getOrderItems().stream().map(orderItemRequest -> new OrderItem(
                    menuRepository.getReferenceById(orderItemRequest.getMenuId()),
                    orderItemRequest.getQuantity()
            )).toList());
            Order order = orderRepository.save(new Order(restaurantTable, OrderStatus.ACCEPTED, orderItems));

            var ref = new Object() {
                Long price;
            };
            orderItems.forEach(orderItem -> {
                Menu menu = orderItem.getMenu();
                ref.price = ref.price + menu.getPrice() * orderItem.getQuantity();
            });

            apiResponse.setMessage("Order accepted\n" +
                    "Price : " + ref.price);
            apiResponse.setSuccess(true);
            apiResponse.setData(order);
        } catch (Exception e) {
            response.setError(true);
            response.setMessage(e.getMessage());
        }
        response.setResponseData(apiResponse);
        return response;
    }

    public ApiResponse changeOrderStatus(UUID orderId, String status) {
        ApiResponse apiResponse;
        try {
            Order order = orderRepository.getReferenceById(orderId);
            order.setOrderStatus(OrderStatus.valueOf(status));
            orderRepository.save(order);
            apiResponse = new ApiResponse(true, order, "Status changed");
        } catch (Exception e) {
            apiResponse = new ApiResponse(false, e.getMessage());
        }
        return apiResponse;
    }
}
