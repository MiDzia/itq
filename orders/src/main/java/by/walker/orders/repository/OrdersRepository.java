package by.walker.orders.repository;

import by.walker.orders.model.Order;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrdersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Order order) {
        String sql = "INSERT INTO orders (number_of_order, total_amount, date, recipient, "
                     + "delivery_address, type_of_payment, type_of_delivery) VALUES (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
            order.getNumberOfOrder(),
            order.getTotalAmount(),
            order.getDate(),
            order.getRecipient(),
            order.getDeliveryAddress(),
            order.getTypeOfPayment().toString(),
            order.getTypeOfDelivery().toString()
        );
    }

    public Optional<Map<String, Object>> findById(Long id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        return Optional.of(jdbcTemplate.queryForMap(sql, id));
    }

    public List<Order> findAllByDateAndSum(LocalDate dateChoose, Long sum) {
        String sql = "SELECT * FROM orders WHERE date::DATE = ? AND total_amount > ?";
        List<Order> orderList = jdbcTemplate.query(sql, new Object[]{dateChoose, sum},
            new BeanPropertyRowMapper<>(Order.class));
        return orderList;
    }

    public List<Order> findAllWithoutProductAndBetweenTime(String product, LocalTime start, LocalTime end) {
        String sql = "SELECT * FROM orders WHERE id in (SELECT id FROM order_details WHERE name <> ?) AND date::time BETWEEN ? AND ?";
        List<Order> orderList = jdbcTemplate.query(sql, new Object[]{product, start, end},
            new BeanPropertyRowMapper<>(Order.class));
        return orderList;
    }
}
