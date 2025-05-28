package iti.jets.model.mappers.custom;

import iti.jets.model.dtos.ShoppingCartSummaryDTO;
import iti.jets.model.dtos.UserDTO;
import iti.jets.model.entities.*;

import java.util.ArrayList;
import java.util.List;

public class MapStructHelpers {
    public static Integer sumQuantities(Product product) {
        if (product.getProductInfos() == null) return 0;
        return product.getProductInfos().stream()
                .mapToInt(ProductInfo::getQuantity)
                .sum();
    }
    public static String getImagePath(Product product) {
        return product.getProductImgs().stream()
                .filter(ProductImg::getIsDefault)
                .findFirst()
                .map(ProductImg::getImg)
                .orElse("");
    }

    public static List<String> getImagesPaths(Product product) {
        return product.getProductImgs().stream()
                .map(ProductImg::getImg)
                .toList();
    }

    public static List<ProductImg> getProductImages(Product product, List<String> images) {
        if (images == null || images.isEmpty()) return product.getProductImgs();
        List<ProductImg> productImgs = new ArrayList<>();
        for (int i = 0; i < images.size(); i++) {
            ProductImg img = new ProductImg();
            img.setProduct(product);
            img.setImg(images.get(i));
            img.setIsDefault(i == 0); // First image is default
            productImgs.add(img);
        }
        return productImgs;
    }

//    public static List<ShoppingCartSummaryDTO> mapCartItems(User user) {
//        return user.getShoppingCart().stream().map(ShoppingCart::getProductInfo)
//    }
}