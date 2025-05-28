package iti.jets.model.mappers;

import iti.jets.annotation.IntegTest;
import iti.jets.model.dtos.*;
import iti.jets.model.entities.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@IntegTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void  testToUserManageDTO(){

        // Given
        User user = createUser();

        // When
        UserManageDTO userManageDTO = userMapper.toUserManageDTO(user);

        // Then
        assertNotNull(userManageDTO);
        assertEquals(user.getUserId(), userManageDTO.getUserId());
        assertEquals(user.getName(), userManageDTO.getName());
        assertEquals(user.getPhoneNumber(), userManageDTO.getPhoneNumber());
        assertEquals(user.getEmail(), userManageDTO.getEmail());
        assertEquals(user.getBirthdate(), userManageDTO.getBirthdate());
        assertEquals(user.getJob(), userManageDTO.getJob());
    }

    @Test
    void  testToUserDTO(){
        // Given
        User user = createUser();

        // When
        UserDTO userDTO = userMapper.toUserDTO(user);

        // Then
        assertNotNull(userDTO);
        assertEquals(user.getUserId(), userDTO.getUserId());
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getPhoneNumber(), userDTO.getPhoneNumber());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getBirthdate(), userDTO.getBirthdate());
        assertEquals(user.getJob(), userDTO.getJob());
        assertEquals(user.getCreditLimit(), userDTO.getCreditLimit());
        assertEquals(user.getInterests(), userDTO.getInterests());
        assertNotNull(userDTO.getAddresses());
        assertEquals(user.getAddresses().size(), userDTO.getAddresses().size());
        UserAddressDTO userAddressDTO = userDTO.getAddresses().get(0);
        assertEquals(user.getAddresses().get(0).getAddressId(), userAddressDTO.getAddressId());
        assertEquals(user.getAddresses().get(0).getStreet(), userAddressDTO.getStreet());
        assertEquals(user.getAddresses().get(0).getState(), userAddressDTO.getState());
        assertEquals(user.getAddresses().get(0).getBuildingNumber(), userAddressDTO.getBuildingNumber());
        assertEquals(user.getAddresses().get(0).getIsDefault(), userAddressDTO.getIsDefault());

    }

    @Test
    void testToUser() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(1L);
        userDTO.setName("John Doe");
        userDTO.setPhoneNumber("1234567890");
        userDTO.setEmail("blabla");
        userDTO.setBirthdate(new Date(1000000000L)); // Example date
        userDTO.setJob("Engineer");
        userDTO.setCreditLimit(new BigDecimal("1000.00"));
        userDTO.setInterests("Reading, Coding");
        UserAddressDTO addressDTO = new UserAddressDTO();
        addressDTO.setAddressId(1L);
        addressDTO.setStreet("123 Main St");
        addressDTO.setState("State");
        addressDTO.setBuildingNumber(10);
        addressDTO.setIsDefault(true);
        userDTO.setAddresses(List.of(addressDTO));

        // When
        User user = userMapper.toUser(userDTO);

        // Then
        assertNotNull(user);
        assertEquals(userDTO.getUserId(), user.getUserId());
        assertEquals(userDTO.getName(), user.getName());

        assertEquals(userDTO.getPhoneNumber(), user.getPhoneNumber());
        assertEquals(userDTO.getEmail(), user.getEmail());
        assertEquals(userDTO.getBirthdate(), user.getBirthdate());
        assertEquals(userDTO.getJob(), user.getJob());
        assertEquals(userDTO.getCreditLimit(), user.getCreditLimit());
        assertEquals(userDTO.getInterests(), user.getInterests());
        assertEquals(userDTO.getBirthdate(), user.getBirthdate());
        // Check addresses
        assertNotNull(user.getAddresses());
        assertEquals(userDTO.getAddresses().size(), user.getAddresses().size());
        UserAddress address = user.getAddresses().get(0);
        assertEquals(addressDTO.getAddressId(), address.getAddressId());
        assertEquals(addressDTO.getStreet(), address.getStreet());
        assertEquals(addressDTO.getState(), address.getState());
        assertEquals(addressDTO.getBuildingNumber(), address.getBuildingNumber());
        assertEquals(addressDTO.getIsDefault(), address.getIsDefault());


    }

    @Test
    void testToUserProfileDataDTO(){
        // Given
        User user = createUser();

        // When
        UserProfileDataDTO userDTO = userMapper.toUserProfileDataDTO(user);

        // Then
        assertNotNull(userDTO);
        assertEquals(user.getUserId(), userDTO.getUserId());
        assertEquals(user.getName(), userDTO.getName());
        assertEquals(user.getPhoneNumber(), userDTO.getPhoneNumber());
        assertEquals(user.getEmail(), userDTO.getEmail());
        assertEquals(user.getBirthdate(), userDTO.getBirthdate());
        assertEquals(user.getJob(), userDTO.getJob());
        assertEquals(user.getCreditLimit(), userDTO.getCreditLimit());
        assertEquals(user.getInterests(), userDTO.getInterests());
        assertNotNull(userDTO.getAddresses());
        assertEquals(user.getAddresses().size(), userDTO.getAddresses().size());
        UserAddressDTO userAddressDTO = userDTO.getAddresses().get(0);
        assertEquals(user.getAddresses().get(0).getAddressId(), userAddressDTO.getAddressId());
        assertEquals(user.getAddresses().get(0).getStreet(), userAddressDTO.getStreet());
        assertEquals(user.getAddresses().get(0).getState(), userAddressDTO.getState());
        assertEquals(user.getAddresses().get(0).getBuildingNumber(), userAddressDTO.getBuildingNumber());
        assertEquals(user.getAddresses().get(0).getIsDefault(), userAddressDTO.getIsDefault());

        List<OrderDTO> orderDTOs = userDTO.getOrders();
        assertNotNull(orderDTOs);
        assertEquals(user.getOrders().size(), orderDTOs.size());
        OrderDTO orderDTO = orderDTOs.get(0);
        assertEquals(user.getOrders().get(0).getOrderId(), orderDTO.getOrderId());
        assertEquals(user.getOrders().get(0).getTotalAmount(), orderDTO.getTotalAmount());
        assertEquals(user.getOrders().get(0).getCreatedAt(), orderDTO.getCreatedAt());
        assertEquals(user.getOrders().get(0).getOrderStatus(), orderDTO.getOrderStatus());

    }

    User createUser(){
        User user = new User();
        user.setUserId(1L);
        user.setName("John Doe");
        user.setPhoneNumber("1234567890");
        user.setEmail("john.doe@example.com");
        user.setPassword("securePassword");
        user.setBirthdate(new Date());
        user.setJob("Engineer");
        user.setCreditLimit(new BigDecimal("1000.00"));
        user.setInterests("Reading, Coding");
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));

// Addresses
        UserAddress address = new UserAddress();
        address.setAddressId(1L);
        address.setUser(user);
        address.setState("State");
        address.setStreet("123 Main St");
        address.setBuildingNumber(10);
        address.setIsDefault(true);
        user.setAddresses(List.of(address));

// Shopping Cart
        ShoppingCart cartItem = new ShoppingCart();
        cartItem.setItemId(1L);
        cartItem.setUser(user);
        cartItem.setProductInfo(null); // Set a ProductInfo object if available
        cartItem.setQuantity(2);
        cartItem.setAddedAt(new Timestamp(System.currentTimeMillis()));
        user.setShoppingCart(List.of(cartItem));

// Orders
        Order order = new Order();
        order.setOrderId(1L);
        order.setUser(user);
        order.setTotalAmount(new BigDecimal("200.00"));
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        order.setUserAddress(address);
        order.setOrderStatus(iti.jets.model.enums.OrderStatus.CONFIRMED);
        order.setOrderItems(new ArrayList<>()); // Add OrderItem objects if needed
        user.setOrders(List.of(order));

// Wishlist
        Wishlist wishlistItem = new Wishlist();
        wishlistItem.setItemId(1L);
        wishlistItem.setUser(user);
        wishlistItem.setProduct(null); // Set a Product object if available
        user.setWishlist(List.of(wishlistItem));
        return user;
    }
}