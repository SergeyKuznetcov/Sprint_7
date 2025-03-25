package ru.yandex.practicum.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Endpoint {
    COURIER("courier"),
    COURIER_LOGIN(COURIER.endpoint + "/login"),
    COURIER_DELETE(COURIER.endpoint + "/%d"),

    ORDERS("orders"),
    ORDERS_CANCEL(ORDERS.endpoint + "/cancel");

    private final String endpoint;
}
