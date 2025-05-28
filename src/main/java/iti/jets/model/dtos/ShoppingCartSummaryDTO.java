package iti.jets.model.dtos;

import lombok.Data;

@Data
public class ShoppingCartSummaryDTO {
    private Long productInfoId;
    private Long product_id;
    private Integer quantity;
}
