package iti.jets.model.mappers;

import iti.jets.model.dtos.WishlistDTO;
import iti.jets.model.entities.Wishlist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WishListMapper {

    WishlistDTO toWishlistDTO(Wishlist wishlist);
    Wishlist toWishlist(WishlistDTO wishlistDTO);
}
