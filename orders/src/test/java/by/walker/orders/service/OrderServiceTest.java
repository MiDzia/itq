//package by.walker.orders.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.verifyNoInteractions;
//import static org.mockito.Mockito.when;
//
//import by.walker.orders.dto.OrderDto;
//import by.walker.orders.exception.OrderNotFoundException;
//import by.walker.orders.mapper.OrderMapper;
//import by.walker.orders.model.Order;
//import by.walker.orders.model.TypeOfDelivery;
//import by.walker.orders.model.TypeOfPayment;
//import by.walker.orders.repository.OrdersRepository;
//import by.walker.orders.util.ErrorMessage;
//import by.walker.orders.util.TimeConverter;
//import java.sql.Timestamp;
//import java.time.LocalDate;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//class OrderServiceTest {
//
//    @Mock
//    private OrdersRepository ordersRepository;
//
//    @Mock
//    private OrderMapper orderMapper;
//
//    @Mock
//    private TimeConverter timeConverter;
//
//    @InjectMocks
//    private OrderService orderService;
//
//    private Order testOrder, testOrder1, testOrder2;
//    private OrderDto testOrderDto, testOrderDto1, testOrderDto2;
//    private Map<String, Object> testMap = new HashMap<>();
//    private Map<String, Object> testMap1 = new HashMap<>();
//    private Map<String, Object> testMap2 = new HashMap<>();
//    private LocalDate testDate;
//    private Long testSum;
//
//    @BeforeEach
//    void setUp() {
//        testOrder = new Order();
//        testOrder.setId(1L);
//        testOrder.setNumberOfOrder("0000520250210");
//        testOrder.setTotalAmount(2450000L);
//        testOrder.setDate(Timestamp.valueOf("2025-02-13 15:10:25.954000"));
//        testOrder.setRecipient("Petr Petrov");
//        testOrder.setDeliveryAddress("234 Pushkina street");
//        testOrder.setTypeOfPayment(TypeOfPayment.valueOf("CASH"));
//        testOrder.setTypeOfDelivery(TypeOfDelivery.valueOf("DOOR_DELIVERY"));
//
//        testOrderDto = new OrderDto(
//            "0000520250210",
//            2450000L,
//            Timestamp.valueOf("2025-02-13 15:10:25.954000"),
//            "Petr Petrov",
//            "234 Pushkina street",
//            TypeOfPayment.valueOf("CASH"),
//            TypeOfDelivery.valueOf("DOOR_DELIVERY")
//        );
//
//        testMap.put("id", 1L);
//        testMap.put("number_of_order", "0000520250210");
//        testMap.put("total_amount", 2450000L);
//        testMap.put("date", Timestamp.valueOf("2025-02-13 15:10:25.954000"));
//        testMap.put("recipient", "Petr Petrov");
//        testMap.put("delivery_address", "234 Pushkina street");
//        testMap.put("type_of_payment", "CASH");
//        testMap.put("type_of_delivery", "DOOR_DELIVERY");
//
//        testOrder1 = new Order();
//        testOrder1.setId(2L);
//        testOrder1.setNumberOfOrder("0000620250210");
//        testOrder1.setTotalAmount(450000L);
//        testOrder1.setDate(Timestamp.valueOf("2025-02-14 15:10:25.954000"));
//        testOrder1.setRecipient("Ivan Petrov");
//        testOrder1.setDeliveryAddress("234 Linina street");
//        testOrder1.setTypeOfPayment(TypeOfPayment.valueOf("CARD"));
//        testOrder1.setTypeOfDelivery(TypeOfDelivery.valueOf("SELF_DELIVERY"));
//
//        testOrderDto1 = new OrderDto(
//            "0000620250210",
//            450000L,
//            Timestamp.valueOf("2025-02-14 15:10:25.954000"),
//            "Ivan Petrov",
//            "234 Linina street",
//            TypeOfPayment.valueOf("CARD"),
//            TypeOfDelivery.valueOf("SELF_DELIVERY")
//        );
//
//        testMap1.put("id", 2L);
//        testMap1.put("number_of_order", "0000620250210");
//        testMap1.put("total_amount", 450000L);
//        testMap1.put("date", Timestamp.valueOf("2025-02-14 15:10:25.954000"));
//        testMap1.put("recipient", "Ivan Petrov");
//        testMap1.put("delivery_address", "234 Linina street");
//        testMap1.put("type_of_payment", "CARD");
//        testMap1.put("type_of_delivery", "SELF_DELIVERY");
//
//        testOrder2 = new Order();
//        testOrder2.setId(2L);
//        testOrder2.setNumberOfOrder("0000720250210");
//        testOrder2.setTotalAmount(150000L);
//        testOrder2.setDate(Timestamp.valueOf("2025-02-14 15:09:25.954000"));
//        testOrder2.setRecipient("Sidor Petrov");
//        testOrder2.setDeliveryAddress("34 Coba street");
//        testOrder2.setTypeOfPayment(TypeOfPayment.valueOf("CASH"));
//        testOrder2.setTypeOfDelivery(TypeOfDelivery.valueOf("SELF_DELIVERY"));
//
//        testOrderDto2 = new OrderDto(
//            "0000720250210",
//            150000L,
//            Timestamp.valueOf("2025-02-14 15:09:25.954000"),
//            "Sidor Petrov",
//            "34 Coba street",
//            TypeOfPayment.valueOf("CASH"),
//            TypeOfDelivery.valueOf("SELF_DELIVERY")
//        );
//
//        testMap2.put("id", 3L);
//        testMap2.put("number_of_order", "0000720250210");
//        testMap2.put("total_amount", 150000L);
//        testMap2.put("date", Timestamp.valueOf("2025-02-14 15:09:25.954000"));
//        testMap2.put("recipient", "Sidor Petrov");
//        testMap2.put("delivery_address", "34 Coba street");
//        testMap2.put("type_of_payment", "CASH");
//        testMap2.put("type_of_delivery", "SELF_DELIVERY");
//
//        testDate = LocalDate.of(2025, 2, 13);
//        testSum = 2450000L;
//    }
//
//    @Test
//    void findOrderById_ShouldReturnOrderDto_WhenOrderExists() {
//        Long orderId = 1L;
//        when(ordersRepository.findById(orderId)).thenReturn(Optional.of(testMap));
//        when(orderMapper.toOrderDto(testMap)).thenReturn(testOrderDto);
//
//        OrderDto result = orderService.findOrderById(orderId);
//
//        assertNotNull(result);
//        assertEquals(testOrderDto.numberOfOrder(), result.numberOfOrder());
//        assertEquals(testOrderDto.totalAmount(), result.totalAmount());
//        assertEquals(testOrderDto.recipient(), result.recipient());
//        assertEquals(testOrderDto.deliveryAddress(), result.deliveryAddress());
//        assertEquals(testOrderDto.typeOfPayment(), result.typeOfPayment());
//        assertEquals(testOrderDto.typeOfDelivery(), result.typeOfDelivery());
//        verify(ordersRepository).findById(orderId);
//        verify(orderMapper).toOrderDto(testMap);
//    }
//
//    @Test
//    void findOrderById_ShouldThrowOrderNotFoundException_WhenOrderDoesNotExist() {
//        Long orderId = 99L;
//        when(ordersRepository.findById(orderId)).thenReturn(Optional.empty());
//
//        OrderNotFoundException exception = assertThrows(OrderNotFoundException.class,
//            () -> orderService.findOrderById(orderId));
//
//        assertEquals(ErrorMessage.ORDER_NOT_FOUND.toString(), exception.getMessage());
//        verify(ordersRepository).findById(orderId);
//        verifyNoInteractions(orderMapper);
//    }
//
//    @Test
//    void findAllByDateAndSum_ShouldReturnOrderDtoList_WhenOrdersExist() {
//        String dateStr = "2025-02-13";
//        when(timeConverter.getDateFromString(dateStr)).thenReturn(testDate);
//        when(ordersRepository.findAllByDateAndSum(testDate, testSum)).thenReturn(List.of(testOrder));
//        when(orderMapper.toOrderDtoFromOrder(testOrder)).thenReturn(testOrderDto);
//
//        List<OrderDto> result = orderService.findAllByDateAndSum(dateStr, testSum);
//
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals(testOrderDto.numberOfOrder(), result.get(0).numberOfOrder());
//        verify(timeConverter).getDateFromString(dateStr);
//        verify(ordersRepository).findAllByDateAndSum(testDate, testSum);
//        verify(orderMapper).toOrderDtoFromOrder(testOrder);
//    }
//
//    @Test
//    void findAllByDateAndSum_ShouldThrowOrderNotFoundException_WhenNoOrdersFound() {
//        String dateStr = "2025-02-13";
//        when(timeConverter.getDateFromString(dateStr)).thenReturn(testDate);
//        when(ordersRepository.findAllByDateAndSum(testDate, testSum)).thenReturn(Collections.emptyList());
//
//        OrderNotFoundException exception = assertThrows(OrderNotFoundException.class,
//            () -> orderService.findAllByDateAndSum(dateStr, testSum));
//
//        assertEquals(ErrorMessage.ORDERS_NOT_FOUND.toString(), exception.getMessage());
//        verify(timeConverter).getDateFromString(dateStr);
//        verify(ordersRepository).findAllByDateAndSum(testDate, testSum);
//        verifyNoInteractions(orderMapper);
//    }
//}
