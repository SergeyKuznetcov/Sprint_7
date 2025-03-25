package ru.yandex.practicum.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.Client;

import java.util.Map;

import static ru.yandex.practicum.constants.Endpoint.*;

public class CourierClient extends Client {
    @Step("Create courier")
    public ValidatableResponse createCourier(Courier courier) {
        return spec()
                .body(courier)
                .when()
                .post(COURIER.getEndpoint())
                .then().log().all();
    }

    @Step("Courier logIn")
    public ValidatableResponse logIn(Courier courier) {
        return spec()
                .body(courier.withFirstName(null))
                .when()
                .post(COURIER_LOGIN.getEndpoint())
                .then().log().all();
    }

    @Step("Delete courier")
    public ValidatableResponse deleteCourier(int id) {
        return spec()
                .body(Map.of("id", id))
                .when()
                .delete(String.format(COURIER_DELETE.getEndpoint(), id))
                .then().log().all();
    }
}
