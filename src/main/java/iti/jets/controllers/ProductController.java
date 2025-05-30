package iti.jets.controllers;

import iti.jets.model.dtos.ProductSummaryDTO;
import iti.jets.model.entities.Product;
import iti.jets.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/hello")
    public String hello() {
        log.info("Hello World");
        return "Hello World";
    }

    @GetMapping
    ResponseEntity<List<ProductSummaryDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getFilteredProducts());
    }
}
