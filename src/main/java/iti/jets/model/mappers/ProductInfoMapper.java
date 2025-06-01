package iti.jets.model.mappers;

import iti.jets.model.dtos.ProductInfoDTO;
import iti.jets.model.dtos.ProductVariationDTO;
import iti.jets.model.entities.ProductInfo;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SizeMapper.class})
public interface ProductInfoMapper {

    ProductInfoDTO toDto(ProductInfo info);
    ProductInfo toEntity(ProductInfoDTO infoDTO);
    ProductInfo variationToEntity(ProductVariationDTO variationDTO);
    List<ProductInfo> variationListToEntityList(List<ProductVariationDTO> variations);

}