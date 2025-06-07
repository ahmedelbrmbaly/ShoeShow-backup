package iti.jets.model.dtos;

import iti.jets.model.enums.OrderStatus;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@ToString
public class OrderDTO {
    private Long orderId;
    private BigDecimal totalAmount;
    private Timestamp createdAt;
    private OrderStatus orderStatus;

}
