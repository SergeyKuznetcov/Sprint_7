package ru.yandex.practicum.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.CommonChecks;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.Assert.assertTrue;

public class CourierChecks extends CommonChecks {
    private static final String COURIER_ID_PATH = "id";

    @Step("Check create courier response")
    public void checkCreated(ValidatableResponse response) {
        checkStatus(response, HTTP_CREATED);
    }

    @Step("Check courier logIn response")
    public Integer checkLogin(ValidatableResponse response) {
        Integer id = response
                .assertThat()
                .statusCode(HTTP_OK)
                .extract()
                .path(COURIER_ID_PATH);
        assertTrue(id != null && id > 0);
        return id;
    }

    @Step("Check delete courier response")
    public void checkDeleted(ValidatableResponse response) {
        checkStatus(response, HTTP_OK);
    }
}
