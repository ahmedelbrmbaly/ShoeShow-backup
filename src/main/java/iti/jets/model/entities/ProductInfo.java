package iti.jets.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "ProductInfo")
@Data
@ToString
public class ProductInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_info_id")
    private Long productInfoId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "size", nullable = false)
    private Size size;

    @Column(name = "color", nullable = false, length = 20)
    private String color;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
