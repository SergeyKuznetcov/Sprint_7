package ru.yandex.practicum;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import ru.yandex.practicum.constants.Error;

public class CommonChecks {
    protected static final String ERROR_MESSAGE_PATH = "message";
    protected static final String STATUS_PATH = "ok";

    @Step("Check response for wrong request")
    public void checkError(ValidatableResponse response, Error expectedError) {
        String message = response.assertThat()
                .statusCode(expectedError.getStatusCode())
                .extract()
                .path(ERROR_MESSAGE_PATH);
        Assert.assertEquals(expectedError.getMessage(), message);
    }

    protected void checkStatus(ValidatableResponse response, int expectedCode) {
        boolean status = response.assertThat()
                .statusCode(expectedCode)
                .extract()
                .path(STATUS_PATH);
        Assert.assertTrue(status);
    }
}
