package iti.jets.model.entities;

import iti.jets.model.enums.Category;
import iti.jets.model.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long product_id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "brand", nullable = false, length = 25)
    private String brand;

    @Column(name = "added_at", nullable = false)
    private Timestamp addedAt;

    @Column(name = "sold", nullable = false)
    private Integer sold;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductInfo> productInfos;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProductImg> productImgs;

    // constructors
    public Product() {
    }

    public Product(Long productId, String name, String description, Category category, Gender gender, BigDecimal price, String brand, Timestamp addedAt, Integer sold, Timestamp updatedAt) {
        this.product_id = productId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.gender = gender;
        this.price = price;
        this.brand = brand;
        this.addedAt = addedAt;
        this.sold = sold;
        this.updatedAt = updatedAt;
    }
    public Product(String name, String description, Category category, Gender gender, BigDecimal price, String brand, Timestamp addedAt, Integer sold, Timestamp updatedAt) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.gender = gender;
        this.price = price;
        this.brand = brand;
        this.addedAt = addedAt;
        this.sold = sold;
        this.updatedAt = updatedAt;
    }

}
