package iti.jets.controllers.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import iti.jets.model.dtos.ProductCreateDTO;
import iti.jets.model.dtos.ProductDetailDTO;
import iti.jets.model.dtos.ProductManageDTO;
import iti.jets.services.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/products")
@Tag(name = "Product Admin Controller", description = "Controller for managing products")
@Slf4j
public class ProductAdminController
{
    private final ProductService productService;

    public ProductAdminController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @Operation(summary = "Get products with search keyword, page, and size",
            description = "Retrieve products with search keyword, page, and size")
    @ApiResponse(responseCode = "200", description = "Products retrieved",
            content = @Content(schema = @Schema(implementation = Page.class)))
    public ResponseEntity<Page<ProductManageDTO>> getProducts(
            @Parameter(description = "search keyword") @RequestParam(value = "searchKeyword", defaultValue = "") String searchKeyword,
            @Parameter(description = "page number") @RequestParam(value = "page", defaultValue = "0")int page,
            @Parameter(description = "page size") @RequestParam(value = "size", defaultValue = "10")int size)
    {

        log.info("Getting products with search keyword {}, page {}, and size {}", searchKeyword, page, size);
        Page<ProductManageDTO> products = productService.getManageProducts(searchKeyword, page, size);
        log.info("Got {} products", products.getNumberOfElements());

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get specific product by ID", description = "Retrieve product Details by ID")
    @ApiResponse(responseCode = "200", description = "Product retrieved",
            content = @Content(schema = @Schema(implementation = ProductDetailDTO.class)))
    public ResponseEntity<ProductDetailDTO> getProduct( @PathVariable Long id)
    {
        log.info("Getting product with id {}", id);
        ProductDetailDTO product = productService.getDetailedProduct(id);
        log.info("Got product with id {}", id);
        return ResponseEntity.ok(product);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create product", description = "Create product")
    @ApiResponse(responseCode = "200", description = "Product created",
            content = @Content(schema = @Schema(implementation = ProductDetailDTO.class)))
    public ResponseEntity<?> createProduct(@Parameter(description = "Product create DTO") @Valid @ModelAttribute ProductCreateDTO productCreateDTO)
    {
        log.info("Creating product with DTO {}", productCreateDTO);
        ProductDetailDTO createdProduct = productService.createProduct(productCreateDTO);
        log.info("Created product with id {}", createdProduct.getProductId());
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping(value= "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update product", description = "Update product")
    @ApiResponse(responseCode = "200", description = "Product updated",
            content = @Content(schema = @Schema(implementation = ProductDetailDTO.class)))
    public ResponseEntity<ProductDetailDTO> updateProduct(@Parameter(description = "Product ID") @PathVariable Long id, @Parameter(description = "Product create DTO") @Valid @ModelAttribute ProductCreateDTO productCreateDTO)
    {
        log.info("Updating product with ID: {}", id);
        ProductDetailDTO updatedProduct = productService.updateProduct(id, productCreateDTO);
        log.info("Updated product with ID: {}", id);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product", description = "Delete product")
    @ApiResponse(responseCode = "204", description = "Product deleted")
    public ResponseEntity<Void> deleteProduct(@Parameter(description = "Product ID") @PathVariable Long id)
    {
        log.info("Deleting product with ID: {}", id);
        productService.deleteProduct(id);
        log.info("Deleted product with ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}