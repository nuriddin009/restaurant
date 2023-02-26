package com.example.restaurant.repository;

import com.example.restaurant.entity.Category;
import com.example.restaurant.projection.CategoryProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    List<CategoryProjection> findAllByOrderByCreatedAt();


}
