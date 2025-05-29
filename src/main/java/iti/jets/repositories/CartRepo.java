package iti.jets.repositories;

import iti.jets.model.dtos.ShoppingCartDTO;
import iti.jets.model.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepo extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByUser_UserId(Long userId);
    ShoppingCart findByItemId(Long itemId);
    Optional<ShoppingCart> findByUser_UserIdAndProductInfo_ProductInfoId(Long userId, Long productInfoId);

}


