package iti.jets.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import iti.jets.model.enums.ShoeSize;
import lombok.Data;

@Data
public class ProductVariationDTO {
    @Schema(description = "Shoe size", example = "SIZE_35", required = true)
    private ShoeSize size;
    @Schema(description = "Product color", example = "Black", required = true)
    private String color;
    @Schema(description = "Product quantity", example = "10", required = true)
    private Integer quantity;
}