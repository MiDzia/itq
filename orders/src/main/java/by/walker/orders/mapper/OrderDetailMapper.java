package by.walker.orders.mapper;

import by.walker.orders.dto.OrderCreateDto;
import by.walker.orders.model.OrderDetails;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailMapper {

    public OrderDetails toOrderDetails(OrderCreateDto dto){
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setProductArticle(dto.productArticle());
        orderDetails.setProductName(dto.productName());
        orderDetails.setAmount(dto.productAmount());
        orderDetails.setUnitPrice(dto.unitPrice());
        return orderDetails;
    }
}
