package com.example.restaurant.repository;

import com.example.restaurant.entity.Menu;
import com.example.restaurant.projection.MenuProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MenuRepository extends JpaRepository<Menu, UUID> {


    @Query(value = "select m.id as id, m.product_name as productName, m.price as price, m.attachment_id as attachmentId\n" +
            "from menu m\n" +
            "         inner join category c on c.id = m.category_id\n" +
            "where ((:categoryId is not null and c.id = :categoryId) or (:categoryId is null))\n" +
            "  and upper(m.product_name) ilike upper(concat('%', :productName, '%'))",
            countQuery = "select m.id as id, m.product_name as productName, m.price as price, m.attachment_id as attachmentId\n" +
                    "from menu m\n" +
                    "         inner join category c on c.id = m.category_id\n" +
                    "where ((:categoryId is not null and c.id = :categoryId) or (:categoryId is null))\n" +
                    "  and upper(m.product_name) ilike upper(concat('%', :productName, '%'))",nativeQuery = true)
    Page<MenuProjection> getMenuBy(UUID categoryId, String productName, Pageable pageable);

}
