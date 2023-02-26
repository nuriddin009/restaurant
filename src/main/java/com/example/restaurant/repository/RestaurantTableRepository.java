package com.example.restaurant.repository;

import com.example.restaurant.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, UUID> {
}
