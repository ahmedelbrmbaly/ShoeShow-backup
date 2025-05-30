package iti.jets.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Schema(description = "Product manage DTO")
@AllArgsConstructor
@NoArgsConstructor
public class ProductManageDTO {
    @Schema(description = "Product id", example = "1", required = true)
    private Long product_id;
    @Schema(description = "Product name", example = "Adidas Ultraboost", required = true)
    private String name;
    @Schema(description = "Product price", example = "100.00", required = true)
    private BigDecimal price;
    @Schema(description = "Product quantity", example = "10", required = true)
    private Integer quantity;

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}