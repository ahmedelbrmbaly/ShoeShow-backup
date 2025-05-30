package iti.jets.repositories;

import iti.jets.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById (Long productId);

    @Query("SELECT p.price FROM Product p WHERE p.product_id = :productId")
    BigDecimal findPriceByProductId(@Param("productId") Long productId);

}
