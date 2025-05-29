package iti.jets.controllers;

import iti.jets.model.dtos.OrderDTO;
import iti.jets.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrdersForAdmin();
        return orders;
    }

    @PutMapping("/{orderId}")
    public void updateOrder(@PathVariable Long orderId,@RequestBody OrderDTO orderDTO) {
        orderService.updateOrder(orderId, orderDTO);
    }
}