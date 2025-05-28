package iti.jets.model.mappers;

import iti.jets.annotation.IntegTest;
import iti.jets.model.dtos.OrderDTO;
import iti.jets.model.entities.Order;
import iti.jets.model.enums.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@IntegTest
class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    void toEntity() {
        // Given
        // Create a sample OrderDTO object
        OrderDTO orderDto = new OrderDTO();
        orderDto.setOrderId(1L);
        orderDto.setOrderStatus(OrderStatus.PENDING);
        orderDto.setTotalAmount(BigDecimal.valueOf(100));

        // When
        // Convert DTO to entity using OrderMapper
        Order order = orderMapper.toOrder(orderDto);

        // Then
        assertNotNull(order);
        assertEquals(1, order.getOrderId());
        assertEquals(OrderStatus.PENDING, order.getOrderStatus());
        assertEquals(BigDecimal.valueOf(100), order.getTotalAmount());
    }

    @Test
    void toDto() {
        // Given
        // Create a sample Order entity object
        Order order = new Order();
        order.setOrderId(1L);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setTotalAmount(BigDecimal.valueOf(100));

        // When
        // Convert entity to DTO using OrderMapper
        OrderDTO orderDto = orderMapper.toOrderDTO(order);

        // Then
        assertNotNull(orderDto);
        assertEquals(1, orderDto.getOrderId());
        assertEquals(OrderStatus.PENDING, orderDto.getOrderStatus());
        assertEquals(BigDecimal.valueOf(100), orderDto.getTotalAmount());

    }

}