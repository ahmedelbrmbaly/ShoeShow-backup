package iti.jets.services;
import iti.jets.exceptions.ResourceNotFoundException;
import iti.jets.model.dtos.PagedResponse;
import iti.jets.model.dtos.ProductDetailDTO;
import iti.jets.model.dtos.ProductSummaryDTO;
import iti.jets.exceptions.FileStorageException;
import iti.jets.model.dtos.*;
import iti.jets.model.entities.Product;
import iti.jets.model.entities.ProductInfo;
import iti.jets.model.entities.Size;
import iti.jets.model.mappers.ProductInfoMapper;
import iti.jets.model.mappers.ProductMapper;
import iti.jets.model.mappers.SizeMapper;
import iti.jets.model.mappers.custom.MapStructHelpers;
import iti.jets.repositories.ProductRepo;
import iti.jets.utils.ProductServiceHelper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {
    private final SizeMapper sizeMapper;
    private final ProductMapper productMapper;
    private final ProductRepo productRepo;
    private final ProductServiceHelper productServiceHelper;
    private final FileStorageService fileStorageService;
    private final ProductInfoMapper productInfoMapper;

    public ProductService(SizeMapper sizeMapper
            , ProductMapper productMapper
            , ProductRepo productRepo
            , ProductServiceHelper productServiceHelper
            , FileStorageService fileStorageService
            , ProductInfoMapper productInfoMapper) {
        this.sizeMapper = sizeMapper;
        this.productMapper = productMapper;
        this.productRepo = productRepo;
        this.productServiceHelper = productServiceHelper;
        this.fileStorageService = fileStorageService;
        this.productInfoMapper = productInfoMapper;
    }


    public PagedResponse<ProductSummaryDTO> getFilteredProducts(
            List<String> brand, List<String> size, List<String> color,
            String orderBy, String gender, String category, String keyWord,
            String pageNumber, String pageSize
    ) {
        Specification<Product> specifications = productServiceHelper.getSpecifications(brand, size, color, gender, category, keyWord);

        Sort sort = productServiceHelper.getSort(orderBy);

        PageRequest pageRequest = productServiceHelper.getPageRequest(pageNumber, pageSize, sort);

        Page<Product> products = productRepo.findAll(specifications, pageRequest);

        List<ProductSummaryDTO> productSummaryDTOS = products.stream()
                .map(productMapper::entityToSummaryDto)
                .toList();

        return PagedResponse.<ProductSummaryDTO>builder()
                .data(productSummaryDTOS)
                .totalPages(products.getTotalPages())
                .totalElements(products.getTotalElements())
                .currentPage(products.getNumber())
                .build();
    }

    public ProductDetailDTO getProductDetail(Long productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + productId));
        return productMapper.entityToDetailDto(product);
    }
    public Page<ProductManageDTO> getManageProducts(String searchKeyword, int page, int size)
    {
        Pageable pageable = PageRequest.of(page,size);
        Page<Product> products = productRepo.findByNameContainingIgnoreCase(searchKeyword, pageable);
        log.info("Retrieved {} products with search keyword '{}'", products.getTotalElements(), searchKeyword);
        return products.map(productMapper::entityToManageDto);
    }

    public ProductDetailDTO getDetailedProduct(Long id)
    {
        Product product = productRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not found with id: "+id));
        return productMapper.entityToDetailDto(product);
    }

    @Transactional
    public ProductDetailDTO createProduct(ProductCreateDTO productCreateDTO)
    {
        log.info("Creating new product: {}", productCreateDTO.getName());
        try {
            // Map DTO to entity without images initially
            Product product = productMapper.createDtotoEntity(productCreateDTO, List.of());
            product.setAddedAt(Timestamp.from(Instant.now()));
            product.setUpdatedAt(Timestamp.from(Instant.now()));
            if (product.getSold() == null) {
                product.setSold(0);
            }

            // Save product to get ID
            Product savedProduct = productRepo.save(product);
            log.debug("Saved product with ID: {}", savedProduct.getProduct_id());

            // Save images if provided
            List<String> imagePaths = List.of();
            if (productCreateDTO.getImages() != null && !productCreateDTO.getImages().isEmpty()) {
                imagePaths = fileStorageService.saveImages(productCreateDTO.getImages(), savedProduct.getProduct_id());
                log.debug("Saved {} images for product ID: {}", imagePaths.size(), savedProduct.getProduct_id());
            } else {
                log.warn("No images provided for product ID: {}", savedProduct.getProduct_id());
            }

            // Update product with images
            if (!imagePaths.isEmpty()) {
                if (savedProduct.getProductImgs() == null)
                    savedProduct.setProductImgs(new ArrayList<>());
                else
                    savedProduct.getProductImgs().clear();

                savedProduct.getProductImgs().addAll(MapStructHelpers.getProductImages(savedProduct, imagePaths));
                savedProduct = productRepo.save(savedProduct);
                log.debug("Updated product with image paths: {}", imagePaths);
            }

            log.info("Successfully created product with ID: {}", savedProduct.getProduct_id());
            return productMapper.entityToDetailDto(savedProduct);
        } catch (IOException e) {
            log.error("Failed to save images for product: {}", productCreateDTO.getName(), e);
            throw new FileStorageException("Failed to save images", e);
        }
    }

    @Transactional
    public ProductDetailDTO updateProduct(Long id, ProductCreateDTO productCreateDTO)
    {
        log.info("Attempting to update product with ID: {}", id);
        Product product = productRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with ID: " + id));

        List<String> imagePaths;
        try {
            if(productCreateDTO.getImages()!= null && !productCreateDTO.getImages().isEmpty())
            {
                fileStorageService.deleteProductImages(id);
                imagePaths = fileStorageService.saveImages(productCreateDTO.getImages(), id);
                log.debug("Deleted previous images and saved {} images for product ID: {}", imagePaths.size(), id);
            }
            else
            {
                imagePaths = MapStructHelpers.getImagesPaths(product);
                log.debug("Using existing images for product ID: {}", id);
            }

            Product updatedProduct = productMapper.createDtotoEntity(productCreateDTO, imagePaths);
            log.debug("Updated product with new DTO: {}", updatedProduct);

            List<ProductInfo> updatedProductInfos = updateProductInfos(product, productCreateDTO.getVariations());
            log.debug("Processed {} product infos for product ID: {}", updatedProductInfos.size(), id);

            updatedProduct.setProduct_id(product.getProduct_id());
            updatedProduct.setUpdatedAt(Timestamp.from(Instant.now()));
            updatedProduct.setAddedAt(product.getAddedAt());
            updatedProduct.setSold(updatedProduct.getSold()==null?0:updatedProduct.getSold());
            log.debug("Updated product with ID: {}: addedAt: {}, updatedAt: {}", updatedProduct.getProduct_id(), updatedProduct.getAddedAt(), updatedProduct.getUpdatedAt());

            updatedProduct.getProductInfos().clear();
            updatedProduct.getProductInfos().addAll(updatedProductInfos);

            productRepo.save(updatedProduct);
            log.info("Successfully updated product with ID: {}", id);

            return productMapper.entityToDetailDto(updatedProduct);

        } catch (IOException e) {
            throw new FileStorageException("Failed to save images for product ID: " + id, e);
        }
    }

    @Transactional
    public void deleteProduct(Long id)
    {
        log.info("Attempting to delete product with ID: {}", id);
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        log.debug("Found product with ID: {}", id);

        fileStorageService.deleteProductImages(id);
        log.debug("Deleted images for product with ID: {}", id);

        productRepo.deleteById(id);
        log.info("Successfully deleted product with ID: {}", id);
    }

    // Helper method to update product infos
    private List<ProductInfo> updateProductInfos(Product product, List<ProductVariationDTO> variations)
    {
        // Map existing ProductInfo by color and size
        Map<String, ProductInfo> existingInfos = product.getProductInfos().stream()
                .collect(Collectors.toMap(
                        info -> (info.getColor() + "|" + (info.getSize() != null ? info.getSize().getSize() : "")).toLowerCase(),
                        info -> info,
                        (a, b) -> a // Keep first if duplicate
                ));

        List<ProductInfo> updatedInfos = new ArrayList<>();

        // Process incoming variations
        for (ProductVariationDTO variation : variations)
        {
            String key = (variation.getColor() + "|" + variation.getSize().getValue()).toLowerCase();
            ProductInfo existingInfo = existingInfos.get(key);

            if (existingInfo != null) {
                // Update existing ProductInfo quantity
                existingInfo.setQuantity(variation.getQuantity());
                updatedInfos.add(existingInfo);
                log.debug("Updated ProductInfo: color={}, size={}, quantity={}",
                        variation.getColor(), variation.getSize().getValue(), variation.getQuantity());
                existingInfos.remove(key); // Mark as processed
            } else {
                // Create new ProductInfo
                ProductInfo newInfo = productInfoMapper.variationToEntity(variation);
                newInfo.setProduct(product);
                // Fetch or create Size
                Size size = sizeMapper.toSize(variation.getSize());

                newInfo.setSize(size);
                updatedInfos.add(newInfo);
                log.debug("Added new ProductInfo: color={}, size={}, quantity={}",
                        variation.getColor(), variation.getSize().getValue(), variation.getQuantity());
            }
        }
        return updatedInfos;
    }
}
