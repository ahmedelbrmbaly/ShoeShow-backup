package iti.jets.services;

import iti.jets.model.dtos.ProductSummaryDTO;
import iti.jets.model.entities.*;
import iti.jets.model.mappers.ProductMapper;
import iti.jets.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class WishlistService {

    private WishListRepo wishListRepo;
    private UserRepo userRepo;
    private ProductRepo productRepo;
    private ProductMapper productMapper;

    public WishlistService(WishListRepo wishListRepo, UserRepo userRepo, ProductRepo productRepo, ProductMapper productMapper) {
        this.wishListRepo = wishListRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }

   public List<ProductSummaryDTO> getWishlist(Long userId){
        List<Wishlist> wishlistItems = wishListRepo.findAll()
                .stream()
                .filter(wishlist -> wishlist.getUser().getUserId().equals(userId))
                .toList();

        return wishlistItems.stream().map(wishlist -> productMapper.entityToSummaryDto(wishlist.getProduct()))
                .toList();
   }
   @Transactional
   public void addToWishlist(Long userId, List<Long> productIds) {
        User user = userRepo.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        for (Long productId : productIds) {
            Product product = productRepo.findById(productId)
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
            boolean exists = wishListRepo.existsByUserUserIdAndProductProductId(userId, productId);
            if (!exists) {
                Wishlist wishlist = new Wishlist();
                wishlist.setUser(user);
                wishlist.setProduct(product);
                wishListRepo.save(wishlist);
            }
        }
   }
   @Transactional
    public void removeFromWishlist(Long userId, Long productId) {
        wishListRepo.deleteByUserUserIdAndProductProductId(userId, productId);
   }
}
