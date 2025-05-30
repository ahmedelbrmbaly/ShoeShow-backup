package iti.jets.repositories.specifications;


import iti.jets.model.entities.Product;
import iti.jets.model.enums.Category;
import iti.jets.model.enums.Gender;
import iti.jets.model.enums.ShoeSize;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSpecifications {

    public Specification<Product> filterByBrand(List<String> brand) {
        return (root, query, criteriaBuilder) -> {
            if (brand == null || brand.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return root.get("brand").in(brand);
        };
    }

    public Specification<Product> filterBySize(List<ShoeSize> sizes) {
        return (root, query, criteriaBuilder) -> {
            if (sizes == null || sizes.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            query.distinct(true);
            List<Long> sizeValues = sizes.stream()
                    .filter(java.util.Objects::nonNull)
                    .map(ShoeSize::getValue)
                    .toList();

            return root.join("productInfos").get("size").get("size").in(sizeValues);
        };
    }


    public Specification<Product> filterByColor(List<String> color) {
        return (root, query, criteriaBuilder) -> {
            if (color == null || color.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            query.distinct(true);
            return root.get("productInfos").get("color").in(color);
        };
    }
    public Specification<Product> filterByGender(Gender gender) {
        return (root, query, criteriaBuilder) -> {
            if (gender == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("gender"), gender);
        };
    }
    public Specification<Product> filterByKeyWord(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            String likePattern = "%" + keyword.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likePattern);
        };
    }
    public Specification<Product> filterByCategory(Category category) {
        return (root, query, criteriaBuilder) -> {
            if (category == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("category"), category);
        };
    }

}
