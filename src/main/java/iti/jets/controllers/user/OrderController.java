package iti.jets.controllers.user;

import iti.jets.model.dtos.OrderDTO;
import iti.jets.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/orders")
@Slf4j
@PreAuthorize("#userId == principal.user.userId")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //http://localhost:8080/api/users/1/orders
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders(@PathVariable Long userId) {
        List<OrderDTO> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public void placeOrder(@PathVariable Long userId)
    {
       orderService.checkout(userId);
    }

}