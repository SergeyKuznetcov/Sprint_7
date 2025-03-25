package ru.yandex.practicum.courier.negative;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.yandex.practicum.constants.Error;
import ru.yandex.practicum.courier.Courier;
import ru.yandex.practicum.courier.CourierChecks;
import ru.yandex.practicum.courier.CourierClient;

import static ru.yandex.practicum.constants.Error.CREATE_COURIER_NOT_ENOUGH_DATA;

@RunWith(JUnitParamsRunner.class)
public class NegativeCourierCreateTests {
    private final CourierClient courierClient = new CourierClient();
    private final CourierChecks courierChecks = new CourierChecks();
    private Integer courierId;

    @Test
    @DisplayName("Create courier request with not full data")
    @Description("Try to create courier without login or password")
    @Parameters({
            ", ",
            "login, ",
            ", pass"
    })
    public void createCourierWithNotFullData(
            String login,
            String password
    ) {
        Courier courier = Courier.builder()
                .login(login)
                .password(password)
                .build();
        ValidatableResponse response = courierClient.createCourier(courier);
        courierChecks.checkError(response, CREATE_COURIER_NOT_ENOUGH_DATA);
    }

    @Test
    @DisplayName("Create courier request with duplicated data")
    @Description("Try to create courier with existed login")
    @Parameters({
            "anyLoginExample, anyPasswordExample"
    })
    public void createCourierWithDuplicatedData(
            String login,
            String password
    ) {
        Courier courier = Courier.builder()
                .login(login)
                .password(password)
                .build();
        ValidatableResponse response = courierClient.createCourier(courier);
        courierChecks.checkCreated(response);

        response = courierClient.logIn(courier);
        courierId = courierChecks.checkLogin(response);

        response = courierClient.createCourier(courier);
        courierChecks.checkError(response, Error.CREATE_COURIER_DUPLICATED_DATA);
    }

    @After
    public void tearDown() {
        if (courierId != null) {
            courierChecks.checkDeleted(courierClient.deleteCourier(courierId));
            courierId = null;
        }
    }
}
