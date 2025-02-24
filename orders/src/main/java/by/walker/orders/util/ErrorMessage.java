package by.walker.orders.util;

public enum ErrorMessage {

    INTERNAL_SERVER_ERROR("Внутренняя ошибка сервера."),
    BAD_REQUEST("Некорректные данные запроса"),
    ORDER_NOT_FOUND("С указанными параметрами заказ не найден"),
    ORDERS_NOT_FOUND("С заданными параметрами заказы отсутствуют");

    ErrorMessage(String description) {
    }
}
