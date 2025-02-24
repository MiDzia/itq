package by.walker.orders.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Тип доставки")
public enum TypeOfDelivery {
    SELF_DELIVERY, DOOR_DELIVERY
}