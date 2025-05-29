package iti.jets.repositories;

import iti.jets.model.entities.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductInfoRepo extends JpaRepository<ProductInfo, Long> {
    @Query("SELECT p.quantity FROM ProductInfo p WHERE p.productInfoId = :productInfoId")
    Integer getProductQuantityByProductInfoId(@Param("productInfoId") Long productInfoId);


}
