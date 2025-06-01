package iti.jets.controllers;

import iti.jets.model.dtos.PagedResponse;
import iti.jets.model.dtos.ProductDetailDTO;
import iti.jets.model.dtos.ProductSummaryDTO;
import iti.jets.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = "application/json")
    ResponseEntity<PagedResponse<ProductSummaryDTO>> getAllProducts(
            @RequestParam(required = false) List<String> brand
            , @RequestParam(required = false) List<String> size
            , @RequestParam(required = false) List<String> color
            , @RequestParam(required = false) String orderBy
            , @RequestParam(required = false) String gender
            , @RequestParam(required = false) String category
            , @RequestParam(required = false) String keyWord
            , @RequestParam(required = false) String pageNumber
            , @RequestParam(required = false) String pageSize
    ) {
        return ResponseEntity.ok(productService.getFilteredProducts(
                brand, size, color, orderBy, gender, category, keyWord, pageNumber, pageSize
        ));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailDTO> getProductDetail(@PathVariable Long productId) {
        log.info("productId: " + productId);
        return ResponseEntity.ok(productService.getProductDetail(productId));
    }
}
