package by.walker.orders.controller;

import by.walker.orders.dto.OrderCreateDto;
import by.walker.orders.dto.OrderDto;
import by.walker.orders.exception.handler.ErrorResponseDto;
import by.walker.orders.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Создание нового заказа")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Заказ успешно создан"),
        @ApiResponse(responseCode = "400", description = "Некорректные данные запроса",
            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
    })
    public void createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        orderService.saveOrder(orderCreateDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение информации о заказе по его id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Информация о заказе успешно получена"),
        @ApiResponse(responseCode = "400", description = "Некорректные данные запроса",
            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
    })
    public OrderDto findOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение информации о заказах созданных в указанную дату и больше указанной суммы")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Информация о заказах успешно получена"),
        @ApiResponse(responseCode = "400", description = "Некорректные данные запроса",
            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
    })
    public List<OrderDto> findAllByDateAndSum(@RequestParam("date") String date, @RequestParam("sum") Long sum) {
        return orderService.findAllByDateAndSum(date, sum);
    }

    @GetMapping("/between")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение информации о заказах не содержащих указанный товар и "
                         + "созданных в заданный промежуток времени")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Информация о заказах успешно получена"),
        @ApiResponse(responseCode = "400", description = "Некорректные данные запроса",
            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
        @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера",
            content = {@Content(schema = @Schema(implementation = ErrorResponseDto.class))}),
    })
    public List<OrderDto> findAllWOProductAndBetweenTime(@RequestParam("product") String product,
        @RequestParam("start") String start,
        @RequestParam("end") String end) {
        return orderService.findAllWithoutProductAndBetweenTime(product, start, end);
    }
}
