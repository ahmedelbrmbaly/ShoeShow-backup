package iti.jets.repositories;

import iti.jets.model.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ProductRepo extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>
{
    Page<Product> findByNameContainingIgnoreCase(String searchKeyword, Pageable pageable);
}
