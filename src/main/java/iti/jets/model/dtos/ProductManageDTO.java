package iti.jets.model.dtos;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductManageDTO {
    private Long product_id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
