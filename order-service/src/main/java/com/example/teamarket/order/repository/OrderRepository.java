package com.example.teamarket.order.repository;

import com.example.teamarket.order.entities.Order;
import com.example.teamarket.order.entities.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Modifying
    @Query(value = "UPDATE orders SET status = :status WHERE order_id = :id", nativeQuery = true)
    void updateStatus(@Param("id") Long id, @Param("status") String status);
}
