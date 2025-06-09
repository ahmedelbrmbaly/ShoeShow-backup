package iti.jets.repositories;

import iti.jets.model.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;


public interface ProductRepo extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Page<Product> findByNameContainingIgnoreCase(String searchKeyword, Pageable pageable);

    @Query("SELECT p.price FROM Product p WHERE p.productId = :productId")
    BigDecimal findPriceByProductId(@Param("productId") Long productId);
}
