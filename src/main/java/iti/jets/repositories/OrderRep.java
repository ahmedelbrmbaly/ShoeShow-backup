package iti.jets.repositories;

import iti.jets.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRep extends JpaRepository<Order,Integer> {
}
