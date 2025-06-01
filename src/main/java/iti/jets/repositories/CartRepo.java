package iti.jets.repositories;

import iti.jets.model.dtos.ShoppingCartDTO;
import iti.jets.model.entities.ShoppingCart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepo extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByUser_UserId(Long userId);
    ShoppingCart findByItemId(Long itemId);
    Optional<ShoppingCart> findByUser_UserIdAndProductInfo_ProductInfoId(Long userId, Long productInfoId);


    @Modifying
    @Transactional
    @Query("DELETE FROM ShoppingCart sc WHERE sc.user.userId = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);
}


