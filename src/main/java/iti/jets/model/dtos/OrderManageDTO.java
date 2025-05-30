package iti.jets.model.dtos;


import iti.jets.model.enums.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderManageDTO {
    private Long orderId;
    private String email;
    private BigDecimal totalAmount;
    private Timestamp createdAt;
    private OrderStatus orderStatus;
}
