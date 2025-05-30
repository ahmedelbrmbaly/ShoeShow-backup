package iti.jets.model.dtos;

import java.math.BigDecimal;
import java.sql.Timestamp;

import iti.jets.model.entities.ProductDetail;
import iti.jets.model.entities.ProductInfo;
import iti.jets.model.entities.User;
import iti.jets.model.enums.ShoeSize;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {
    // private int itemId;
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
