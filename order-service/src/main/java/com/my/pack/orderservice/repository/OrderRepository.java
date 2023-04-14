package com.my.pack.orderservice.repository;

import com.my.pack.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrderRepository extends JpaRepository<Order,Long> {
}
