package iti.jets.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import iti.jets.model.enums.ShoeSize;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "Data transfer object for product variation details")
public class ProductInfoDTO {

    @Schema(description = "Unique identifier for the product variation (optional for creation)", example = "1")
    private Long productInfoId;

    @NotBlank(message = "Color is required")
    @Size(max = 30, message = "Color must not exceed 30 characters")
    @Schema(description = "Color of the product", example = "Black", requiredMode = Schema.RequiredMode.REQUIRED)
    private String color;

    @NotNull(message = "Size is required")
    @Schema(description = "Shoe size", example = "SIZE_38", requiredMode = Schema.RequiredMode.REQUIRED)
    private ShoeSize size;

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity must be zero or positive")
    @Schema(description = "Available quantity", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer quantity;
}