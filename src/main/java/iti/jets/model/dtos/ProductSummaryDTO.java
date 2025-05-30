package iti.jets.model.dtos;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductSummaryDTO {
    private Long product_id;
    private String name;
    private BigDecimal price;
    private String img;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
