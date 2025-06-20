package iti.jets.model.dtos;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductSummaryDTO {
    private Long productId;
    private String name;
    private BigDecimal price;
    private String img;
}
