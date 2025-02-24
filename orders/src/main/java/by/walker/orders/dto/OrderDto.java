package by.walker.orders.dto;

import by.walker.orders.model.TypeOfDelivery;
import by.walker.orders.model.TypeOfPayment;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Timestamp;

@Schema(description = "DTO отображения информации о заказе")
public record OrderDto(
    @Schema(description = "Номер заказа", example = "0000120250213")
    String numberOfOrder,

    @Schema(description = "Общая сумма заказа", example = "5000")
    Long totalAmount,

    @Schema(description = "Дата заказа", example = "2024-02-21T12:30:00.000Z")
    Timestamp date,

    @Schema(description = "Получатель заказа", example = "Carl Siviy")
    String recipient,

    @Schema(description = "Адрес доставки", example = "128 Lenina street")
    String deliveryAddress,

    @Schema(description = "Тип оплаты", example = "CASH")
    TypeOfPayment typeOfPayment,

    @Schema(description = "Тип доставки", example = "DOOR_DELIVERY")
    TypeOfDelivery typeOfDelivery
) {
}
