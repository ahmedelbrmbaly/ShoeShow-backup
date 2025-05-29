package iti.jets.repositories;

import iti.jets.model.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepo extends JpaRepository<OrderItem , Long> {
    List<OrderItem> findByOrder_OrderId(Long orderId);
}
