package iti.jets.repositories;

import iti.jets.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById (Long productId);
}
