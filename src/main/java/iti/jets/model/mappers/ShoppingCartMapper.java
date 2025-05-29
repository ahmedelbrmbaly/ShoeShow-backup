package iti.jets.model.mappers;

import iti.jets.model.dtos.ShoppingCartDTO;
import iti.jets.model.dtos.ShoppingCartSummaryDTO;
import iti.jets.model.entities.ShoppingCart;
import iti.jets.model.mappers.custom.MapStructHelpers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" ,
        uses = {MapStructHelpers.class} ,
        imports = {MapStructHelpers.class})
public interface ShoppingCartMapper {

    @Mapping(source = "user.userId", target = "userId")
    @Mapping(source = "productInfo.product.product_id", target = "productId")
    @Mapping(source = "productInfo.productInfoId", target = "productInfoId")
    @Mapping(source = "productInfo.product.name", target = "name")
    // @Mapping(expression = "java(shoppingCart.getProductInfo().getSize())" , target = "size" ,qualifiedByName = "toShoeSize")
    @Mapping(source = "productInfo.size.size", target = "size")
    @Mapping(source = "productInfo.color", target = "color")
    @Mapping(source = "productInfo.product.price", target = "price")
    @Mapping(expression = "java(MapStructHelpers.getImagePath(shoppingCart.getProductInfo().getProduct()))", target = "img")
    @Mapping(source = "addedAt", target = "addedAt")
    ShoppingCartDTO toShoppingCartDto(ShoppingCart shoppingCart);

    ShoppingCart toShoppingCart(ShoppingCartDTO shoppingCartDTO);


}


