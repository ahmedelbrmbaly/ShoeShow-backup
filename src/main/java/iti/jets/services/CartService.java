package iti.jets.services;

import java.nio.file.AccessDeniedException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import iti.jets.exceptions.ResourceNotFoundException;
import iti.jets.exceptions.ValidationException;
import iti.jets.model.dtos.ShoppingCartDTO;
import iti.jets.model.dtos.ShoppingCartSummaryDTO;
import iti.jets.model.entities.*;
import iti.jets.model.enums.ShoeSize;
import iti.jets.model.mappers.ShoppingCartMapper;
import iti.jets.repositories.CartRepo;
import iti.jets.repositories.ProductInfoRepo;
import iti.jets.repositories.ProductRepo;
import iti.jets.repositories.UserRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartService {

    private ShoppingCartMapper shoppingCartMapper;
    private CartRepo cartRepo;
    private ProductInfoRepo productInfoRepo;
    private UserRepo userRepo;

    public CartService(ShoppingCartMapper shoppingCartMapper, CartRepo cartRepo, ProductInfoRepo productInfoRepo, UserRepo userRepo) {
        this.shoppingCartMapper = shoppingCartMapper;
        this.cartRepo = cartRepo;
        this.productInfoRepo = productInfoRepo;
        this.userRepo = userRepo;
    }

    public List<ShoppingCartDTO> getCartItemsByUserId(Long userId) {
        List<ShoppingCart> shoppingCarts = cartRepo.findByUser_UserId(userId);
        return shoppingCarts.stream()
                .map(shoppingCartMapper::toShoppingCartDto)
                .collect(Collectors.toList());
    }


    public boolean checkQuantity(Long productInfoId, int requiredQuantity) {
        Integer itemQuantity = productInfoRepo.getProductQuantityByProductInfoId(productInfoId);
        return itemQuantity >= requiredQuantity;
    }


    public void addToCart(Long userId, ShoppingCartSummaryDTO shoppingCartSummaryDTO) {

        // Validate user existence
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ValidationException("User not found"));

        // Validate product info existence
        ProductInfo productInfo = productInfoRepo.findById(shoppingCartSummaryDTO.getProductInfoId())
                .orElseThrow(() -> new ValidationException("ProductInfo not found"));


        if (!checkQuantity(shoppingCartSummaryDTO.getProductInfoId(), shoppingCartSummaryDTO.getQuantity())) {
            throw new ResourceNotFoundException("Out of stock");
        }

        // check if item exists
        Optional<ShoppingCart> existingCartItem =
                cartRepo.findByUser_UserIdAndProductInfo_ProductInfoId(userId, shoppingCartSummaryDTO.getProductInfoId());

        ShoppingCart shoppingCart;
        if (existingCartItem.isPresent())
        {
            shoppingCart = existingCartItem.get();
            shoppingCart.setQuantity(shoppingCartSummaryDTO.getQuantity());
        }
        else
        {
            shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            shoppingCart.setProductInfo(productInfo);
            shoppingCart.setQuantity(shoppingCartSummaryDTO.getQuantity());
            shoppingCart.setAddedAt(new Timestamp(System.currentTimeMillis()));
        }
        cartRepo.save(shoppingCart);
    }


    public void deleteCartItem(Long itemId)
    {
        // check if item exists
        if(cartRepo.findByItemId(itemId) == null)
        {
            throw new ValidationException("Cart item not found");
        }
        cartRepo.deleteById(itemId);
    }

    public void updateCartItem(Long userId, ShoppingCartSummaryDTO shoppingCartSummaryDTO) {
        addToCart(userId , shoppingCartSummaryDTO);
    }
}
