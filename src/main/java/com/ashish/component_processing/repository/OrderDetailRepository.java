package com.ashish.component_processing.repository;

import com.ashish.component_processing.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findOrderDetailByCustomerEmail(String customerEmail);

    Optional<OrderDetail> findOrderDetailByCustomerEmailAndOrderId(String customerEmail, Long orderId);
}
