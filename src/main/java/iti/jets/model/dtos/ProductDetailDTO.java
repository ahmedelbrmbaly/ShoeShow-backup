package iti.jets.model.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDetailDTO {
    @Schema(description = "The unique identifier of the product")
    private Long product_id;
    @Schema(description = "The name of the product")
    private String name;
    @Schema(description = "The description of the product")
    private String description;
    @Schema(description = "The price of the product")
    private BigDecimal price;
    @Schema(description = "The list of images of the product")
    private List<String> img = new ArrayList<>();
    @Schema(description = "The list of product information of the product")
    private List<ProductInfoDTO> productInfos = new ArrayList<>();

    public ProductDetailDTO() {
    }

    public ProductDetailDTO(Long product_id, String name, String description, BigDecimal price, List<String> img, List<ProductInfoDTO> productInfos) {
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
        this.productInfos = productInfos;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public List<ProductInfoDTO> getProductInfos() {
        return productInfos;
    }

    public void setProductInfos(List<ProductInfoDTO> productInfos) {
        this.productInfos = productInfos;
    }
    
    
}