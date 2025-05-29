package iti.jets.controllers;

import iti.jets.model.dtos.ShoppingCartDTO;
import iti.jets.model.dtos.ShoppingCartSummaryDTO;
import iti.jets.services.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}/cart")
    public List<ShoppingCartDTO> getCartItems(@PathVariable("userId") Long userId)
    {
        return cartService.getCartItemsByUserId(userId);
    }

    @PostMapping("/{userId}/cart")
    public void addToCart(@PathVariable Long userId, @RequestBody ShoppingCartSummaryDTO shoppingCartSummaryDTO) {
        cartService.addToCart(userId, shoppingCartSummaryDTO);
    }


    @DeleteMapping("/{userId}/cart/items/{itemId}")
    public void deleteCartItem(@PathVariable("itemId") Long itemId)
    {
        cartService.deleteCartItem(itemId);
    }

    @PutMapping("/{userId}/cart")
    public void updateCartItem(@PathVariable Long userId, @RequestBody ShoppingCartSummaryDTO shoppingCartSummaryDTO) {
        cartService.updateCartItem(userId, shoppingCartSummaryDTO);
    }


}
