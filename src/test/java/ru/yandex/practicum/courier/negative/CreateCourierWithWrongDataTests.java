package ru.yandex.practicum.courier.negative;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.courier.Courier;
import ru.yandex.practicum.courier.CourierChecks;
import ru.yandex.practicum.courier.CourierClient;

import java.util.Arrays;
import java.util.Collection;

import static ru.yandex.practicum.constants.Error.CREATE_COURIER_NOT_ENOUGH_DATA;

@RunWith(Parameterized.class)
@DisplayName("Create Courier With Wrong Data Tests")
public class CreateCourierWithWrongDataTests {
    private final CourierClient courierClient = new CourierClient();
    private final CourierChecks courierChecks = new CourierChecks();

    @Parameterized.Parameter
    public String login;
    @Parameterized.Parameter(1)
    public String password;

    @Parameterized.Parameters(name = " {index}: login = \"{0}\"; password = \"{1}\"")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"", ""},
                {"login", ""},
                {"", "pas"}
        });
    }

    @Test
    @Description("Try to create courier without login or password")
    public void createCourierWithNotFullData() {
        Courier courier = Courier.builder()
                .login(login)
                .password(password)
                .build();
        ValidatableResponse response = courierClient.createCourier(courier);
        courierChecks.checkError(response, CREATE_COURIER_NOT_ENOUGH_DATA);
    }
}
