package ru.yandex.practicum.courier;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.yandex.practicum.CommonChecks;

import java.net.HttpURLConnection;

import static org.junit.Assert.assertTrue;

public class CourierChecks extends CommonChecks {
    @Step("Check create courier response")
    public void checkCreated(ValidatableResponse response) {
        boolean isCreated = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("ok");
        assertTrue(isCreated);
    }

    @Step("Check courier logIn response")
    public Integer checkLogin(ValidatableResponse response) {
        Integer id = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("id");
        assertTrue(id != null && id > 0);
        return id;
    }

    @Step("Check delete courier response")
    public void checkDeleted(ValidatableResponse response) {
        boolean isDeleted = response
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("ok");
        assertTrue(isDeleted);
    }
}
