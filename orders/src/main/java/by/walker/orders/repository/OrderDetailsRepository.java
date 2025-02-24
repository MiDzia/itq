package by.walker.orders.repository;

import by.walker.orders.model.OrderDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDetailsRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderDetailsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveOrderDetails(OrderDetails orderDetails) {
        String sql = "INSERT INTO order_details (product_article, product_name, amount, unit_price, order_id) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,
            orderDetails.getProductArticle(),
            orderDetails.getProductName(),
            orderDetails.getAmount(),
            orderDetails.getUnitPrice(),
            orderDetails.getOrderId()
        );
    }
}
