package iti.jets.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import iti.jets.model.enums.Category;
import iti.jets.model.enums.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "Data transfer object for creating or updating a product")
public class ProductCreateDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    @Schema(description = "Product name", example = "Adidas Ultraboost", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    @Schema(description = "Product description", example = "High-performance running shoes")
    private String description;

    @NotNull(message = "Category is required")
    @Schema(description = "Product category", example = "CASUAL", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = {"SNEAKERS", "CLASSIC", "CASUAL"})
    private Category category;

    @NotNull(message = "Gender is required")
    @Schema(description = "Target gender", example = "MALE", requiredMode = Schema.RequiredMode.REQUIRED, allowableValues = {"MALE", "FEMALE"})
    private Gender gender;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    @Digits(integer = 10, fraction = 2, message = "Price must have up to 10 digits and 2 decimal places")
    @Schema(description = "Product price", example = "129.99", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal price;

    @NotBlank(message = "Brand is required")
    @Size(max = 50, message = "Brand must not exceed 50 characters")
    @Schema(description = "Product brand", example = "Adidas", requiredMode = Schema.RequiredMode.REQUIRED)
    private String brand;

    @PositiveOrZero(message = "Sold must be zero or positive")
    @Schema(description = "Number of units sold", example = "0", defaultValue = "0")
    private Integer sold = 0;

//    @NotNull(message = "Images list cannot be null")
//    @NotEmptyMultipartFileList(message = "At least one non-empty image is required")
    @Schema(description = "List of product images (multipart files)", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<MultipartFile> images = new ArrayList<>();

//    @NotEmpty(message = "At least one variation is required")
//    @Valid
    @Schema(description = "List of product variations (color, size, quantity)", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<ProductVariationDTO> variations = new ArrayList<>();
}