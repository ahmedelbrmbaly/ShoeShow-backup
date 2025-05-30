package iti.jets.services;

import iti.jets.exceptions.BadRequestException;
import iti.jets.exceptions.ResourceNotFoundException;
import iti.jets.model.dtos.PagedResponse;
import iti.jets.model.dtos.ProductDetailDTO;
import iti.jets.model.dtos.ProductSummaryDTO;
import iti.jets.model.entities.Product;
import iti.jets.model.enums.Category;
import iti.jets.model.enums.Gender;
import iti.jets.model.enums.ShoeSize;
import iti.jets.model.mappers.ProductMapper;
import iti.jets.repositories.ProductRepo;
import iti.jets.repositories.specifications.ProductSpecifications;
import iti.jets.utils.ProductServiceHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepo productRepo;
    private final ProductServiceHelper productServiceHelper;
    public ProductService(
            ProductMapper productMapper
            , ProductRepo productRepo
            , ProductServiceHelper productServiceHelper
    ) {
        this.productMapper = productMapper;
        this.productRepo = productRepo;
        this.productServiceHelper = productServiceHelper;
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
}
