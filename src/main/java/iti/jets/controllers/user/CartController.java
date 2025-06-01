package iti.jets.controllers.user;

import iti.jets.model.dtos.ShoppingCartDTO;
import iti.jets.model.dtos.ShoppingCartSummaryDTO;
import iti.jets.services.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/cart")
@PreAuthorize("#userId == principal.user.userId")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public List<ShoppingCartDTO> getCartItems(@PathVariable("userId") Long userId)
    {
        return cartService.getCartItemsByUserId(userId);
    }
    @PostMapping
    public void addToCart(@PathVariable Long userId, @RequestBody ShoppingCartSummaryDTO shoppingCartSummaryDTO) {
        cartService.addToCart(userId, shoppingCartSummaryDTO);
    }
    @DeleteMapping("/items/{itemId}")
    public void deleteCartItem(@PathVariable Long userId, @PathVariable Long itemId) {
        cartService.deleteCartItem(itemId);
    }

    @PutMapping
    public void updateCartItem(@PathVariable Long userId, @RequestBody ShoppingCartSummaryDTO shoppingCartSummaryDTO) {

        cartService.updateCartItem(userId, shoppingCartSummaryDTO);
    }

}
