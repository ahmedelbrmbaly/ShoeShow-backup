package iti.jets.services;

import iti.jets.model.dtos.SalesStatisticsDTO;
import iti.jets.model.dtos.StatisticsDTO;
import iti.jets.repositories.OrderRepo;
import iti.jets.repositories.ProductRepo;
import iti.jets.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class StatisticsService {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final UserRepo userRepo;
    public StatisticsService(ProductRepo productRepo, OrderRepo orderRepo, UserRepo userRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
    }
    public StatisticsDTO getStatistics() {
        return StatisticsDTO.builder()
                .totalProducts(getTotalProductsCount())
                .totalSales(getTotalSalesCount())
                .totalOrders(getTotalOrdersCount())
                .totalUsers(getTotalUsersCount())
                .salesStatistics( getMonthlySalesStats() )
                .build();
    }


    public long getTotalProductsCount() {
        return productRepo.count();
    }
    public long getTotalOrdersCount() {
        return orderRepo.count();
    }
    public long getTotalUsersCount() {
        return userRepo.count();
    }
    public BigDecimal getTotalSalesCount() {
        return orderRepo.sumTotalAmount();
    }
    public ArrayList<SalesStatisticsDTO> getMonthlySalesStats() {
        ArrayList<SalesStatisticsDTO> salesStatistics = new ArrayList<>();
        orderRepo.getMonthlySalesStatisticsRaw().forEach(row -> {
            SalesStatisticsDTO stats = new SalesStatisticsDTO();
            stats.setMonth((String) row[0]);
            stats.setTotalSales((BigDecimal) row[1]);
            stats.setTotalOrders((Long) row[2]);
            salesStatistics.add(stats);
        });
        return salesStatistics;
    }


}
