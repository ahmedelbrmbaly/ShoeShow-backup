package iti.jets.controllers;

import iti.jets.model.dtos.PagedResponse;
import iti.jets.model.dtos.ProductDetailDTO;
import iti.jets.model.dtos.ProductSummaryDTO;
import iti.jets.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@Slf4j
@Tag(name = "Product", description = "Product management APIs")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "Get filtered products",
            description = "Get a paginated list of products with optional filters"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = PagedResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping(produces = "application/json")
    ResponseEntity<PagedResponse<ProductSummaryDTO>> getAllProducts(
            @Parameter(description = "Filter by brand names") @RequestParam(required = false) List<String> brand
            , @Parameter(description = "Filter by sizes") @RequestParam(required = false) List<String> size
            , @Parameter(description = "Filter by colors") @RequestParam(required = false) List<String> color
            , @Parameter(description = "Sort order criteria") @RequestParam(required = false) String orderBy
            , @Parameter(description = "Filter by gender") @RequestParam(required = false) String gender
            , @Parameter(description = "Filter by category") @RequestParam(required = false) String category
            , @Parameter(description = "Search by keyword") @RequestParam(required = false) String keyWord
            , @Parameter(description = "Page number") @RequestParam(required = false) String pageNumber
            , @Parameter(description = "Number of items per page") @RequestParam(required = false) String pageSize
    ) {
        return ResponseEntity.ok(productService.getFilteredProducts(
                brand, size, color, orderBy, gender, category, keyWord, pageNumber, pageSize
        ));
    }

    @Operation(
            summary = "Get product details",
            description = "Get detailed information about a specific product by ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema(implementation = ProductDetailDTO.class))),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailDTO> getProductDetail(
            @Parameter(description = "ID of the product to retrieve") @PathVariable Long productId) {
        log.info("productId: " + productId);
        return ResponseEntity.ok(productService.getProductDetail(productId));
    }
}