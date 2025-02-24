package by.walker.orders.repository;

import by.walker.orders.model.Order;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class OrdersRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrdersRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Long> saveOrder(Order order) {
        String sql = "INSERT INTO orders (number_of_order, total_amount, date, recipient, "
                     + "delivery_address, type_of_payment, type_of_delivery) VALUES (?,?,?,?,?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getNumberOfOrder());
            ps.setLong(2, order.getTotalAmount());
            ps.setTimestamp(3, order.getDate());
            ps.setString(4, order.getRecipient());
            ps.setString(5, order.getDeliveryAddress());
            ps.setString(6, order.getTypeOfPayment().toString());
            ps.setString(7, order.getTypeOfDelivery().toString());
            return ps;
        }, keyHolder);

        Map<String, Object> keys = keyHolder.getKeys();
        if (keys != null && keys.containsKey("id")) {
            return Optional.of((Long) keys.get("id"));
        }
        return Optional.empty();
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
