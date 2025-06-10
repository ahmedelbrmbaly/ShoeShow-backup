package iti.jets.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import iti.jets.model.enums.Category;
import iti.jets.model.enums.Gender;
import lombok.Data;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDetailDTO {
    @Schema(description = "The unique identifier of the product")
    private Long productId;
    @Schema(description = "The name of the product")
    private String name;
    @Schema(description = "The description of the product")
    private String description;
    @Schema(description = "The price of the product")
    private BigDecimal price;
    @Schema(description = "The list of images of the product")
    private List<String> img = new ArrayList<>();
    @Schema(description = "The list of product information of the product")
    private List<ProductInfoDTO> productInfos = new ArrayList<>();
    @Schema(description = "The category of the product")
    private Category category;
    @Schema(description = "The gender of the product")
    private Gender gender;
    @Schema(description = "The brand of the product")
    private String brand;
}