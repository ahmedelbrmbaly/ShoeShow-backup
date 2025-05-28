package iti.jets.services;

import iti.jets.model.dtos.ProductSummaryDTO;
import iti.jets.model.dtos.WishlistDTO;
import iti.jets.model.entities.Product;
import iti.jets.model.mappers.ProductMapper;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    private final ProductMapper productMapper;

    public WishlistService(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

}
