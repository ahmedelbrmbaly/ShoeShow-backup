package iti.jets.services;

import iti.jets.exceptions.BadRequestException;
import iti.jets.exceptions.ResourceNotFoundException;
import iti.jets.model.dtos.OrderDTO;
import iti.jets.model.dtos.OrderManageDTO;
import iti.jets.model.dtos.ShoppingCartDTO;
import iti.jets.model.entities.*;
import iti.jets.model.enums.OrderStatus;
import iti.jets.model.mappers.OrderMapper;
import iti.jets.repositories.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderItemRepo orderItemRepo;
    @Autowired
    private ProductInfoRepo productInfoRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartService cartService;


    public List<OrderDTO> getOrdersByUserId(Long userId){
        List<Order> orders = orderRepo.findByUser_UserId(userId);
        if(orders == null)
        {
            throw new ResourceNotFoundException("Not have orders yet");
        }
        return orders.stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    public Page<OrderManageDTO> getAllOrdersForAdmin(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Order> orders = orderRepo.findAll(pageable);
        return orders.map(orderMapper::toOrderManageDto);
    }

    public void updateOrder(Long orderId, OrderDTO updatedOrderDTO) {
        Optional<Order> optionalOrder = orderRepo.findById(orderId.intValue());
        if (optionalOrder.isEmpty()) {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
        Order order = optionalOrder.get();
        order.setOrderStatus(updatedOrderDTO.getOrderStatus());

        // if status -> cancel ->  update quantity , credit card
        if(OrderStatus.CANCELLED.equals(updatedOrderDTO.getOrderStatus()))
        {

            // update quantity
            List<OrderItem> orderItems = orderItemRepo.findByOrder_OrderId(orderId);
            orderItems.forEach(orderItem -> {
                Integer quantity = productInfoRepo.getProductQuantityByProductInfoId(orderItem.getProductInfo().getProductInfoId());
                productInfoRepo.updateProductQuantity(orderItem.getProductInfo().getProductInfoId() ,
                        orderItem.getQuantity() + quantity);
                System.out.println(orderItem.getQuantity());
            });

            // update credit card
            User user = userRepo.findById(order.getUser().getUserId()).get();
            user.setCreditLimit(BigDecimal.valueOf(user.getCreditLimit().intValue() + order.getTotalAmount().intValue()));
        }
        orderRepo.save(order);
    }

    public void checkout(Long userId)
    {
        List<ShoppingCartDTO> shoppingCartDTOS =  cartService.getCartItemsByUserId(userId);
        if (shoppingCartDTOS == null || shoppingCartDTOS.isEmpty()) {
            throw new BadRequestException("Shopping cart is empty");
        }

       // get total amount
       int totalAmount = getTotalAmount(shoppingCartDTOS);

        // check creditLimit
        if(checkCreditLimit(userId , totalAmount))
        {
            shoppingCartDTOS.forEach(shoppingCartDTO ->
            {
                if(!cartService.checkQuantity(shoppingCartDTO.getProductInfoId() , shoppingCartDTO.getQuantity()))
                {
                    throw new ResourceNotFoundException(shoppingCartDTO.getName() + " out of stock");
                }
            });

            // get user info
            User user = userRepo.findById(userId).get();
            Order order = new Order();
            order.setUser(user);
            order.setTotalAmount(BigDecimal.valueOf(totalAmount));
            order.setUserAddress(user.getAddresses()
                    .stream()
                    .filter(UserAddress::getIsDefault)
                    .findFirst().get());

            order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            // save order in database
            orderRepo.save(order);

            // fill list of order items
            List<OrderItem> orderItems = shoppingCartDTOS.stream().map(shoppingCartDTO -> {
                OrderItem orderItem = new OrderItem();

                orderItem.setProductInfo(productInfoRepo.findById(shoppingCartDTO.getProductInfoId())
                        .orElseThrow(() -> new ResourceNotFoundException("ProductInfo not found")));

                orderItem.setQuantity(shoppingCartDTO.getQuantity());
                orderItem.setPriceAtPurchase(BigDecimal.valueOf(shoppingCartDTO.getPrice().intValue()));
                orderItem.setOrder(order);

                // update quantity of each product
                ProductInfo productInfo = productInfoRepo.findById(shoppingCartDTO.getProductInfoId()).get();
                productInfo.setQuantity(productInfo.getQuantity() - shoppingCartDTO.getQuantity());

                return orderItem;
            }).collect(Collectors.toList());

            // Save order items
            orderItemRepo.saveAll(orderItems);

            order.setOrderItems(orderItems);

            // clear shopping cart
            cartService.deleteCartItems(userId);

            // update credit limit
            user.setCreditLimit(BigDecimal.valueOf(user.getCreditLimit().intValue() - totalAmount));
            userRepo.save(user);
        }
        else
        {
            throw new ResourceNotFoundException("Total amount exceed credit limit");
        }
    }

    private int getTotalAmount(List<ShoppingCartDTO> shoppingCartDTOS) {
        return shoppingCartDTOS.stream()
                .mapToInt(shoppingCartDTO -> getPrice(shoppingCartDTO.getProductId()) * shoppingCartDTO.getQuantity())
                .sum();
    }


    private int getPrice(Long productId)
    {
       return (productRepo.findPriceByProductId(productId)).intValue();
    }

    private boolean checkCreditLimit(Long userId , int totalAmount)
    {
       int creditLimit = userRepo.findCreditLimitByUserId(userId).intValue();

       return totalAmount <= creditLimit;
    }

}


