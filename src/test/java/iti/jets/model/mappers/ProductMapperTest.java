package iti.jets.model.mappers;

import iti.jets.annotation.IntegTest;
import iti.jets.model.dtos.*;
import iti.jets.model.entities.Product;
import iti.jets.model.entities.ProductImg;
import iti.jets.model.entities.ProductInfo;
import iti.jets.model.entities.Size;
import iti.jets.model.enums.Category;
import iti.jets.model.enums.Gender;
import iti.jets.model.enums.ShoeSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@IntegTest
class ProductMapperTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    void testToProductManageDTO() {
        // Given
        Product product = createDummyProduct();

        // When
        ProductManageDTO productDTO = productMapper.entityToManageDto(product);

        // Then
        assertNotNull(productDTO);
        assertEquals(1, productDTO.getProduct_id());
        assertEquals("Cool Jacket", productDTO.getName());
        assertEquals(new BigDecimal("149.99"), productDTO.getPrice());
        assertEquals(15, productDTO.getQuantity());

    }

    @Test
    void testProductSummaryDTO(){
        // Given
        Product product = createDummyProduct();

        // When
        ProductSummaryDTO productDTO = productMapper.entityToSummaryDto(product);

        // Then
        assertNotNull(productDTO);
        assertEquals(1, productDTO.getProduct_id());
        assertEquals("Cool Jacket", productDTO.getName());
        assertEquals("https://example.com/img1.jpg", productDTO.getImg());
        assertEquals(new BigDecimal("149.99"), productDTO.getPrice());
    }

    @Test
    void testProductDetailDTO(){
        // Given
        Product product = createDummyProduct();

        // When
        ProductDetailDTO productDTO = productMapper.entityToDetailDto(product);

        // Then
        assertNotNull(productDTO);
        assertEquals(1, productDTO.getProduct_id());
        assertEquals("Cool Jacket", productDTO.getName());
        assertEquals("High-quality leather jacket for winter", productDTO.getDescription());
        assertEquals(new BigDecimal("149.99"), productDTO.getPrice());

        List<ProductInfoDTO> infos = productDTO.getProductInfos();
        assertNotNull(infos);
        assertEquals(2, infos.size());

        ProductInfoDTO first = infos.get(0);
        assertEquals("Black", first.getColor());
        assertEquals(38L, first.getSize().getValue());
        assertEquals(10, first.getQuantity());

        ProductInfoDTO second = infos.get(1);
        assertEquals("Black", second.getColor());
        assertEquals(40L, second.getSize().getValue());
        assertEquals(5, second.getQuantity());

    }

    @Test
    void testFromProductCreateDTO() {
        // Given
        ProductCreateDTO dto = new ProductCreateDTO();
        dto.setName("Cool Shoes");
        dto.setDescription("Stylish running shoes");
        dto.setCategory(Category.CASUAL);
        dto.setGender(Gender.MALE);
        dto.setPrice(new BigDecimal("99.99"));
        dto.setBrand("Sportify");
        dto.setSold(15);

        ProductVariationDTO var1 = new ProductVariationDTO();
        var1.setSize(ShoeSize.SIZE_38);
        var1.setColor("Red");
        var1.setQuantity(10);

        ProductVariationDTO var2 = new ProductVariationDTO();
        var2.setSize(ShoeSize.SIZE_40);
        var2.setColor("Blue");
        var2.setQuantity(5);

        dto.setVariations(Arrays.asList(var1, var2));

        List<String> imageFileNames = Arrays.asList("img1.jpg", "img2.jpg");

        // When
        Product product = productMapper.createDtotoEntity(dto, imageFileNames);

        // Then
        assertNotNull(product);
        assertEquals("Cool Shoes", product.getName());
        assertEquals("Stylish running shoes", product.getDescription());
        assertEquals(Category.CASUAL, product.getCategory());
        assertEquals(Gender.MALE, product.getGender());
        assertEquals(new BigDecimal("99.99"), product.getPrice());
        assertEquals("Sportify", product.getBrand());
        assertEquals(15, product.getSold());
//        assertNotNull(product.getAddedAt());
//        assertNotNull(product.getUpdatedAt());

        // ProductInfos
        List<ProductInfo> infos = product.getProductInfos();
        System.out.println("Product Infos: " + infos);
        assertNotNull(infos);
        assertEquals(2, infos.size());

        ProductInfo info1 = infos.get(0);
        assertEquals(38L, info1.getSize().getSize());
        assertEquals("Red", info1.getColor());
        assertEquals(10, info1.getQuantity());
        assertSame(product, info1.getProduct());

        ProductInfo info2 = infos.get(1);
        assertEquals(40L, info2.getSize().getSize());
        assertEquals("Blue", info2.getColor());
        assertEquals(5, info2.getQuantity());
        assertSame(product, info2.getProduct());

        // ProductImgs
        List<ProductImg> imgs = product.getProductImgs();
        assertNotNull(imgs);
        assertEquals(2, imgs.size());

        assertEquals("img1.jpg", imgs.get(0).getImg());
        assertTrue(imgs.get(0).getIsDefault());
        assertSame(product, imgs.get(0).getProduct());

        assertEquals("img2.jpg", imgs.get(1).getImg());
        assertFalse(imgs.get(1).getIsDefault());
        assertSame(product, imgs.get(1).getProduct());
    }

    public static Product createDummyProduct() {
        Product product = new Product(1L,
                "Cool Jacket",
                "High-quality leather jacket for winter",
                Category.CLASSIC,
                Gender.MALE,
                new BigDecimal("149.99"),
                "LeatherCo",
                new Timestamp(System.currentTimeMillis()),
                25,
                new Timestamp(System.currentTimeMillis())
        );

        // Product Info
        Size size38 = new Size(38L);
        Size size40 = new Size(40L);
        ProductInfo info1 = new ProductInfo(null, product,size38 , "Black", 10);
        ProductInfo info2 = new ProductInfo(null, product, size40, "Black", 5);
        product.setProductInfos(Arrays.asList(info1, info2));

        // ProductImgs
        ProductImg img1 = new ProductImg(null, product, "https://example.com/img1.jpg", true);
        ProductImg img2 = new ProductImg(null, product, "https://example.com/img2.jpg", false);
        product.setProductImgs(Arrays.asList(img1, img2));

        return product;
    }
}