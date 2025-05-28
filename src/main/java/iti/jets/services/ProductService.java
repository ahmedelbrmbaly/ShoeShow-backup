package iti.jets.services;

import iti.jets.model.dtos.ProductDetailDTO;
import iti.jets.model.dtos.ProductManageDTO;
import iti.jets.model.dtos.ProductSummaryDTO;
import iti.jets.model.entities.Product;
import iti.jets.model.mappers.ProductMapper;
import iti.jets.repositories.ProductRepo;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final ProductMapper productMapper;
    private ProductRepo productRepo;
    public ProductService(ProductMapper productMapper, ProductRepo productRepo) {
        this.productMapper = productMapper;
        this.productRepo = productRepo;
    }

    public List<ProductSummaryDTO> getFilteredProducts(){
        List<Product> products = productRepo.findAll();
        log.info(products.getFirst().getName());
//        log.info(products.getFirst().getProductImgs().getFirst().getImg());
        List<ProductSummaryDTO> res = new ArrayList<>();
//        products.forEach(product -> {
//            ProductSummaryDTO p = productMapper.entityToSummaryDto(product);
//            res.add(p);
//        });
        return res;
    }

}
