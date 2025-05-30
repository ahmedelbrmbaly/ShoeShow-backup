package iti.jets.repositories;

import iti.jets.model.dtos.ProductManageDTO;
import iti.jets.model.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepo extends JpaRepository<Product, Long>
{
    Page<Product> findByNameContainingIgnoreCase(String searchKeyword, Pageable pageable);
}
