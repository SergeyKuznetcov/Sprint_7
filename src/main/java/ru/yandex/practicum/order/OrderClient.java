package ru.yandex.practicum.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.Client;

import static ru.yandex.practicum.constants.Endpoint.ORDERS;
import static ru.yandex.practicum.constants.Endpoint.ORDERS_CANCEL;

public class OrderClient extends Client {
    @Step("Create order request")
    public ValidatableResponse createOrder(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDERS.getEndpoint())
                .then().log().all();
    }

    @Step("Cancel order request")
    public ValidatableResponse cancelOrder(int track) {
        return spec()
                .queryParam("track", track)
                .when()
                .put(ORDERS_CANCEL.getEndpoint())
                .then().log().all();
    }

    @Step("Get orders list")
    public ValidatableResponse getOrdersList() {
        return spec()
                .when()
                .get(ORDERS.getEndpoint())
                .then().log().all();
    }
}
