package iti.jets.model.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
@Builder
public class StatisticsDTO {
    private Long totalOrders;
    private Long totalProducts;
    private Long totalUsers;
    private BigDecimal totalSales;
    private ArrayList<SalesStatisticsDTO> salesStatistics;
}
