//package by.walker.orders.controller;
//
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import by.walker.orders.dto.OrderCreateDto;
//import by.walker.orders.service.OrderService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@ExtendWith(MockitoExtension.class)
//class OrderControllerTest {
//
//    @Mock
//    private OrderService orderService;
//
//    @InjectMocks
//    private OrderController orderController;
//
//    private MockMvc mockMvc;
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
//        objectMapper = new ObjectMapper();
//    }
//
//    @Test
//    void createOrder_ShouldReturnOk_WhenOrderIsCreated() throws Exception {
//        OrderCreateDto orderCreateDto = new OrderCreateDto(
//            720000L,
//            "Vlad Zehir",
//            "72 Sadovaya street",
//            "CASH",
//            "DOOR_DELIVERY"
//        );
//
//        doNothing().when(orderService).saveOrder(orderCreateDto);
//
//        mockMvc.perform(post("/orders/new")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(orderCreateDto)))
//            .andExpect(status().isOk());
//
//        verify(orderService, times(1)).saveOrder(orderCreateDto);
//    }
//}
