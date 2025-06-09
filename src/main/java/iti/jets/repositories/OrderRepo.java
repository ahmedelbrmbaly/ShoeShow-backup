package iti.jets.repositories;

import iti.jets.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Integer> {
    List<Order> findByUser_UserId(Long useId);

    @Query("SELECT SUM(o.totalAmount) FROM Order o")
    BigDecimal sumTotalAmount();

    @Query(value = """
    SELECT DATE_FORMAT(o.created_at, '%Y-%m') AS month,
           SUM(oi.price_at_purchase * oi.quantity) AS totalSales,
           COUNT(DISTINCT o.order_id) AS totalOrders
    FROM OrderItem oi
    JOIN `Order` o ON oi.order_id = o.order_id
    GROUP BY DATE_FORMAT(o.created_at, '%Y-%m')
    ORDER BY DATE_FORMAT(o.created_at, '%Y-%m')
    """, nativeQuery = true)
    List<Object[]> getMonthlySalesStatisticsRaw();
}
