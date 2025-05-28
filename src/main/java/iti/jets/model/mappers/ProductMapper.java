package iti.jets.model.mappers;

import iti.jets.model.dtos.ProductCreateDTO;
import iti.jets.model.dtos.ProductDetailDTO;
import iti.jets.model.dtos.ProductManageDTO;
import iti.jets.model.dtos.ProductSummaryDTO;
import iti.jets.model.entities.Product;
import iti.jets.model.entities.ProductInfo;
import iti.jets.model.mappers.custom.MapStructHelpers;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MapStructHelpers.class, ProductInfoMapper.class},
        imports = {MapStructHelpers.class})
public interface ProductMapper {

    @Mapping(target = "quantity", expression = "java(MapStructHelpers.sumQuantities(product))")
    ProductManageDTO entityToManageDto(Product product);

    @Mapping(target = "img", expression = "java(MapStructHelpers.getImagePath(product))")
    ProductSummaryDTO entityToSummaryDto(Product product);

    @Mapping(target = "img", expression = "java(MapStructHelpers.getImagesPaths(product))")
    ProductDetailDTO entityToDetailDto(Product product);

    @Mapping(target = "productImgs",expression = "java(MapStructHelpers.getProductImages(product,images))")
    @Mapping(target = "productInfos", source = "productCreateDTO.variations")
    Product createDtotoEntity(ProductCreateDTO productCreateDTO, List<String> images);

    @AfterMapping
    default void linkProductInfos(@MappingTarget Product product) {
        if (product.getProductInfos() != null) {
            for (ProductInfo info : product.getProductInfos()) {
                info.setProduct(product);
            }
        }
    }
}
