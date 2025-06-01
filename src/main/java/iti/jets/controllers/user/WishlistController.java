package iti.jets.controllers.user;

import iti.jets.model.dtos.ProductSummaryDTO;
import iti.jets.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/wishlist")
@PreAuthorize("#userId == principal.user.userId")
@Slf4j
public class WishlistController {

    private WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    ResponseEntity<List<ProductSummaryDTO>> getWishlist(@PathVariable Long userId) {
        List<ProductSummaryDTO> wishlist = wishlistService.getWishlist(userId);
        return ResponseEntity.ok(wishlist);
    }
    @PostMapping
    public ResponseEntity<Void> addToWishlist(@PathVariable Long userId, @RequestBody List<Long> productIds) {
        wishlistService.addToWishlist(userId, productIds);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeFromWishlist(@PathVariable Long userId, @PathVariable Long productId) {
        wishlistService.removeFromWishlist(userId, productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
