package iti.jets.services;

import iti.jets.model.dtos.OrderDTO;
import iti.jets.model.entities.Order;
import iti.jets.model.entities.OrderItem;
import iti.jets.model.entities.User;
import iti.jets.model.enums.OrderStatus;
import iti.jets.model.mappers.OrderMapper;
import iti.jets.repositories.OrderItemRepo;
import iti.jets.repositories.OrderRepo;
import iti.jets.repositories.ProductInfoRepo;
import iti.jets.repositories.UserRepo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    private OrderMapper orderMapper;


    public List<OrderDTO> getOrdersByUserId(Long userId){
        List<Order> orders = orderRepo.findByUser_UserId(userId);

        return orders.stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrdersForAdmin() {
        return orderRepo.findAll().stream()
                .map(orderMapper::toOrderDTO)
                .collect(Collectors.toList());
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

}
