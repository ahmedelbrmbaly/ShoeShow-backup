package iti.jets.model.dtos;

import java.math.BigDecimal;
import java.sql.Timestamp;

import iti.jets.model.enums.ShoeSize;
import lombok.*;

@Data
public class ShoppingCartDTO {
     private int itemId;
    private Long productId;
    private Long productInfoId;
    private Long userId;
    private String name;
    private Long size;
    private String color;
    private BigDecimal price;    
    private Integer quantity;
    private String img;
    private Timestamp addedAt;

    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
                "productId=" + productId +
                ", productInfoId=" + productInfoId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", addedAt=" + addedAt +
                '}';
    }
}
