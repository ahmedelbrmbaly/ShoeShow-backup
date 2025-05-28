package iti.jets.model.dtos;

import iti.jets.model.enums.ShoeSize;
import lombok.Data;

@Data
public class ProductInfoDTO {
    private Long productInfoId;
    private String color;
    private ShoeSize size;
    private Integer quantity;
}
