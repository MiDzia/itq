package by.walker.orders.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Тип оплаты")
public enum TypeOfPayment {
    CARD, CASH
}
