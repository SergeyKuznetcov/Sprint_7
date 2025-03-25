package ru.yandex.practicum.courier.positive;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.courier.Courier;
import ru.yandex.practicum.courier.CourierChecks;
import ru.yandex.practicum.courier.CourierClient;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
@DisplayName("Positive courier tests")
public class CourierTests {
    private final CourierClient courierClient = new CourierClient();
    private final CourierChecks courierChecks = new CourierChecks();
    private Integer courierId;

    @Parameterized.Parameter
    public String login;
    @Parameterized.Parameter(1)
    public String password;
    @Parameterized.Parameter(2)
    public String firstName;

    @Parameterized.Parameters(name = " {index}: login = \"{0}\"; password = \"{1}\"; firstName = \"{2}\"")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"UserLoginExample", "password", "UserNameExample"},
                {"Ф", "П", ""},
                {"Другой логин", "Пароль", null}
        });
    }

    @Test
    @Description("Check create, login and delete requests for courier")
    public void courierTest() {
        Courier courier = new Courier(login, password, firstName);
        ValidatableResponse response = courierClient.createCourier(courier);
        courierChecks.checkCreated(response);

        response = courierClient.logIn(courier);
        courierId = courierChecks.checkLogin(response);
    }

    @After
    public void tearDown() {
        if (courierId != null) {
            courierChecks.checkDeleted(courierClient.deleteCourier(courierId));
        }
    }
}