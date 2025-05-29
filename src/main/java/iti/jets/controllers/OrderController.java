package iti.jets.controllers;

import iti.jets.model.dtos.OrderDTO;
import iti.jets.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    //http://localhost:8080/api/users/1/orders
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getOrders(@PathVariable Long userId) {
        List<OrderDTO> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }


    // TODO second method

}