package iti.jets.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ProductImg")
public class ProductImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "img", nullable = false, length = 100)
    private String img; // path

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;
}
