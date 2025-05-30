package iti.jets.utils;

import iti.jets.exceptions.BadRequestException;
import iti.jets.model.entities.Product;
import iti.jets.model.enums.Category;
import iti.jets.model.enums.Gender;
import iti.jets.model.enums.ShoeSize;
import iti.jets.repositories.specifications.ProductSpecifications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class ProductServiceHelper {
    private final ProductSpecifications productSpecifications;
    public ProductServiceHelper(ProductSpecifications productSpecifications) {
        this.productSpecifications = productSpecifications;
    }
    public Specification<Product> getSpecifications(
            List<String> brand, List<String> size, List<String> color
            , String gender, String category, String keyWord
    ) {
        List<ShoeSize> sizeList = size == null ? null : size.stream()
                .map(val -> {
                    try {
                        return ShoeSize.fromValue(Long.valueOf(val));
                    } catch (NumberFormatException e) {
                        log.warn("Invalid size string: {}", val);
                        throw new BadRequestException("Invalid size string: " + val);
                    }catch (IllegalArgumentException e) {
                        throw new BadRequestException(e.getMessage());
                    }
                })
                .filter(Objects::nonNull)
                .toList();

        Category categoryValue = category == null ? null : Category.parseCategory(category);
        Gender genderValue = gender == null ? null : Gender.parseGender(gender);

        return productSpecifications.filterByBrand(brand)
                .and(productSpecifications.filterBySize(sizeList))
                .and(productSpecifications.filterByColor(color))
                .and(productSpecifications.filterByGender(genderValue))
                .and(productSpecifications.filterByKeyWord(keyWord))
                .and(productSpecifications.filterByCategory(categoryValue));
    }

    public Sort getSort(String orderBy) {
        Sort sort;
        if (orderBy != null) {
            sort = switch (orderBy) {
                case "newArrival" -> Sort.by(Sort.Direction.DESC, "addedAt");
                case "bestseller" -> Sort.by(Sort.Direction.DESC, "sold");
                default -> throw new BadRequestException("Invalid orderBy: " + orderBy);
            };
        } else {
            sort = Sort.by(Sort.Direction.DESC, "addedAt");
        }
        return sort;
    }

    public PageRequest getPageRequest(String pageNumber, String pageSize, Sort sort) {
        int page = 0;
        int sizePerPage = 10;

        try {
            if (pageNumber != null) {
                page = Integer.parseInt(pageNumber);
                if(page < 0) throw new BadRequestException("PageNumber can't be negative: " + page);
            }
        } catch (NumberFormatException e) {
            log.warn("Invalid pageNumber {}", pageNumber);
            throw new BadRequestException("Invalid pageNumber " + pageNumber);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }

        try {
            if (pageSize != null) {
                sizePerPage = Integer.parseInt(pageSize);
                if(sizePerPage <= 0) throw new BadRequestException("pageSize can't be negative " + pageNumber);
            }
        } catch (NumberFormatException e) {
            log.warn("Invalid pageSize {}", pageSize);
            throw new BadRequestException("Invalid pageSize " + pageSize);
        } catch (BadRequestException e) {
            throw new BadRequestException(e.getMessage());
        }

        return PageRequest.of(page, sizePerPage, sort);
    }


}
