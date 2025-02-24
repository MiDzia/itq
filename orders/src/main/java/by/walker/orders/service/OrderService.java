package by.walker.orders.service;

import by.walker.orders.dto.OrderCreateDto;
import by.walker.orders.dto.OrderDto;
import by.walker.orders.exception.OrderNotFoundException;
import by.walker.orders.mapper.OrderDetailMapper;
import by.walker.orders.mapper.OrderMapper;
import by.walker.orders.model.Order;
import by.walker.orders.model.OrderDetails;
import by.walker.orders.repository.OrderDetailsRepository;
import by.walker.orders.repository.OrdersRepository;
import by.walker.orders.util.ErrorMessage;
import by.walker.orders.util.TimeConverter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Value("${spring.external-api}")
    private String url;

    private final TimeConverter timeConverter;

    private final OrdersRepository ordersRepository;

    private final ExternalApiService externalApiService;

    private final OrderMapper orderMapper;

    private final OrderDetailMapper orderDetailMapper;

    private final OrderDetailsRepository orderDetailsRepository;

    public OrderService(TimeConverter timeConverter, OrdersRepository ordersRepository,
        ExternalApiService externalApiService,
        OrderMapper orderRowMapper, OrderDetailMapper orderDetailMapper,
        OrderDetailsRepository orderDetailsRepository) {
        this.timeConverter = timeConverter;
        this.ordersRepository = ordersRepository;
        this.externalApiService = externalApiService;
        this.orderMapper = orderRowMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public void saveOrder(OrderCreateDto orderCreateDto) {
        OrderDetails orderDetails = orderDetailMapper.toOrderDetails(orderCreateDto);
        Order order = orderMapper.toOrder(orderCreateDto);

        Long totalSum = orderDetails.getUnitPrice() * orderDetails.getAmount();

        order.setTotalAmount(totalSum);
        order.setNumberOfOrder(getNumber());
        order.setDate(new Timestamp(System.currentTimeMillis()));

        Optional<Long> orderId = ordersRepository.saveOrder(order);

        if (orderId.isPresent()) {
            orderDetails.setOrderId(orderId.get());
            orderDetailsRepository.saveOrderDetails(orderDetails);
        } else {
            throw new OrderNotFoundException(ErrorMessage.ORDER_NOT_FOUND);
        }
    }

    private String getNumber() {
        return externalApiService.getNumber(url);
    }

    public OrderDto findOrderById(Long id) {
        return ordersRepository.findById(id)
            .map(orderMapper::toOrderDto)
            .orElseThrow(() -> new OrderNotFoundException(ErrorMessage.ORDER_NOT_FOUND));
    }

    public List<OrderDto> findAllByDateAndSum(String date, Long sum) {
        LocalDate dateChoose = timeConverter.getDateFromString(date);
        List<Order> orderList = ordersRepository.findAllByDateAndSum(dateChoose, sum);
        if (orderList.isEmpty()) {
            throw new OrderNotFoundException(ErrorMessage.ORDERS_NOT_FOUND);
        }
        return orderList.stream()
            .map(orderMapper::toOrderDtoFromOrder)
            .toList();
    }

    public List<OrderDto> findAllWithoutProductAndBetweenTime(String product, String start, String end) {
        LocalTime startTime = timeConverter.getTimeFromString(start);
        LocalTime endTime = timeConverter.getTimeFromString(end);
        List<Order> orderList = ordersRepository.findAllWithoutProductAndBetweenTime(product, startTime, endTime);
        if (orderList.isEmpty()) {
            throw new OrderNotFoundException(ErrorMessage.ORDERS_NOT_FOUND);
        }
        return orderList.stream()
            .map(orderMapper::toOrderDtoFromOrder)
            .toList();
    }
}
