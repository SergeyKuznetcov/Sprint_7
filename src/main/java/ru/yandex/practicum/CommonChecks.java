package ru.yandex.practicum;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import ru.yandex.practicum.constants.Error;

public class CommonChecks {
    private static final String ERROR_MESSAGE_PARAMETER_NAME = "message";

    @Step("Check response for wrong request")
    public void checkError(ValidatableResponse response, Error expectedError) {
        String message = response.assertThat()
                .statusCode(expectedError.getStatusCode())
                .extract()
                .path(ERROR_MESSAGE_PARAMETER_NAME);
        Assert.assertEquals(expectedError.getMessage(), message);
    }
}
