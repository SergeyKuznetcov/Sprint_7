package ru.yandex.practicum.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import ru.yandex.practicum.CommonChecks;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

public class OrderChecks extends CommonChecks {
    private static final String TRACK_PATH = "track";

    @Step("Check create order response")
    public Integer createOrderCheck(ValidatableResponse response) {
        Integer track = response
                .assertThat()
                .statusCode(HTTP_CREATED)
                .extract()
                .path(TRACK_PATH);
        Assert.assertNotNull(track);
        return track;
    }

    @Step("Check cancel order response")
    public void checkCanceled(ValidatableResponse response) {
        checkStatus(response, HTTP_OK);
    }

    @Step("Check orders list is not null")
    public void checkOrderListNotNull(ValidatableResponse response) {
        response.assertThat()
                .statusCode(HTTP_OK)
                .body("orders", Matchers.notNullValue());
    }
}
