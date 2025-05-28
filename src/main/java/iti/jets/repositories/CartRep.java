package iti.jets.repositories;

import iti.jets.model.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRep extends JpaRepository<ShoppingCart,Integer> {
}
