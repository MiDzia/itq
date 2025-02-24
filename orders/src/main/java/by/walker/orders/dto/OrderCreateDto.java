package by.walker.orders.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для создания заказа")
public record OrderCreateDto(

    @Schema(description = "Артикул товара", example = "1001")
    Integer productArticle,

    @Schema(description = "Наименование товара" , example = "vedro")
    String productName,

    @Schema(description = "Количество товара", example = "3")
    Integer productAmount,

    @Schema(description = "Стоимость единицы товара", example = "2400")
    Long unitPrice,

    @Schema(description = "Получатель заказа", example = "Ivan Dorn")
    String recipient,

    @Schema(description = "Адрес доставки", example = "333 Modern street")
    String deliveryAddress,

    @Schema(description = "Тип оплаты", example = "CASH")
    String typeOfPayment,

    @Schema(description = "Тип доставки", example = "DOOR_DELIVERY")
    String typeOfDelivery
) {
}
