package iti.jets.controllers.admin;

import io.swagger.v3.oas.annotations.Parameter;
import iti.jets.model.dtos.OrderDTO;
import iti.jets.model.dtos.OrderManageDTO;
import iti.jets.model.dtos.ProductManageDTO;
import iti.jets.model.entities.Order;
import iti.jets.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderManageDTO>> getAllOrders(
//            @Parameter(description = "search keyword") @RequestParam(value = "searchKeyword", defaultValue = "") String searchKeyword,
            @Parameter(description = "page number") @RequestParam(value = "page", defaultValue = "0")int page,
            @Parameter(description = "page size") @RequestParam(value = "size", defaultValue = "10")int size)
     {

         Page<OrderManageDTO> orders = orderService.getAllOrdersForAdmin(page, size);
         return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}")
    public void updateOrder(@PathVariable Long orderId,@RequestBody OrderDTO orderDTO) {
        orderService.updateOrder(orderId, orderDTO);
    }
}