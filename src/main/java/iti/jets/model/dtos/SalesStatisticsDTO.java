package iti.jets.model.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SalesStatisticsDTO {
    private String month;
    private BigDecimal totalSales;
    private Long totalOrders;
}
