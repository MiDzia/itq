package by.walker.orders.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для создания заказа")
public record OrderCreateDto(
    @Schema(description = "Общая сумма заказа", example = "5000")
    Long total_amount,

    @Schema(description = "Получатель заказа", example = "Ivan Dorn")
    String recipient,

    @Schema(description = "Адрес доставки", example = "333 Modern street")
    String delivery_address,

    @Schema(description = "Тип оплаты", example = "CASH")
    String type_of_payment,

    @Schema(description = "Тип доставки", example = "DOOR_DELIVERY")
    String type_of_delivery
) {
}
