package org.example._1_back_end.repository;

import org.example._1_back_end.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrdersRepository extends JpaRepository<Orders, String> {
    @Query(value = "SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1", nativeQuery = true)
    String getLastId();
}
