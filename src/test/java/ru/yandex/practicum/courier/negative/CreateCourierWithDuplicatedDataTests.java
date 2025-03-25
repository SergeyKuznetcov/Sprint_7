package ru.yandex.practicum.courier.negative;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.constants.Error;
import ru.yandex.practicum.courier.Courier;
import ru.yandex.practicum.courier.CourierChecks;
import ru.yandex.practicum.courier.CourierClient;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
@DisplayName("Create Courier With Duplicated Data Tests")
public class CreateCourierWithDuplicatedDataTests {
    private final CourierClient courierClient = new CourierClient();
    private final CourierChecks courierChecks = new CourierChecks();
    private Integer courierId;

    @Parameterized.Parameter
    public String login;
    @Parameterized.Parameter(1)
    public String password;

    @Parameterized.Parameters(name = " {index}: login = \"{0}\"; password = \"{1}\"")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"anyLoginExample", "anyPasswordExample"}
        });
    }

    @Test
    @Description("Try to create courier with existed login")
    public void createCourierWithDuplicatedData() {
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
