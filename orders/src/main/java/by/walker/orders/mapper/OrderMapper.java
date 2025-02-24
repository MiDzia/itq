package by.walker.orders.mapper;


import by.walker.orders.dto.OrderCreateDto;
import by.walker.orders.dto.OrderDto;
import by.walker.orders.model.Order;
import by.walker.orders.model.TypeOfDelivery;
import by.walker.orders.model.TypeOfPayment;
import java.sql.Timestamp;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDto toOrderDto(Map<String, Object> orderMap) {
        return new OrderDto(
            (String) orderMap.get("number_of_order"),
            (Long) orderMap.get("total_amount"),
            (Timestamp) orderMap.get("date"),
            (String) orderMap.get("recipient"),
            (String) orderMap.get("delivery_address"),
            TypeOfPayment.valueOf(orderMap.get("type_of_payment").toString()),
            TypeOfDelivery.valueOf(orderMap.get("type_of_delivery").toString())
        );
    }

    public OrderDto toOrderDtoFromOrder(Order order) {
        return new OrderDto(
            order.getNumberOfOrder(),
            order.getTotalAmount(),
            order.getDate(),
            order.getRecipient(),
            order.getDeliveryAddress(),
            order.getTypeOfPayment(),
            order.getTypeOfDelivery()
        );
    }

    public Order toOrder(OrderCreateDto dto) {
        Order order = new Order();
        order.setTotalAmount(dto.total_amount());
        order.setRecipient(dto.recipient());
        order.setDeliveryAddress(dto.delivery_address());
        order.setTypeOfPayment(TypeOfPayment.valueOf(dto.type_of_payment()));
        order.setTypeOfDelivery(TypeOfDelivery.valueOf(dto.type_of_delivery()));
        return order;
    }
}
