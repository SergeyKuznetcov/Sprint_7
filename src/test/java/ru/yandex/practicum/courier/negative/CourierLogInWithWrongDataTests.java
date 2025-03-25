package ru.yandex.practicum.courier.negative;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.constants.Error;
import ru.yandex.practicum.courier.Courier;
import ru.yandex.practicum.courier.CourierChecks;
import ru.yandex.practicum.courier.CourierClient;

import java.util.Arrays;
import java.util.Collection;

import static ru.yandex.practicum.constants.Error.LOGIN_NON_EXISTENT_DATA;
import static ru.yandex.practicum.constants.Error.LOGIN_NOT_ENOUGH_DATA;

@AllArgsConstructor
@RunWith(Parameterized.class)
@DisplayName("Courier LogIn With Wrong Data Tests")
public class CourierLogInWithWrongDataTests {
    private final CourierClient courierClient = new CourierClient();
    private final CourierChecks courierChecks = new CourierChecks();

    private String login;
    private String password;
    private Error expectedError;

    @Parameterized.Parameters(name = " {index}: login = {0}; password = {1}; expectedError = {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"", "", LOGIN_NOT_ENOUGH_DATA},
                {"login", "", LOGIN_NOT_ENOUGH_DATA},
                {"", "pass", LOGIN_NOT_ENOUGH_DATA},
                {"DummyLogin", "DummyPass", LOGIN_NON_EXISTENT_DATA}
        });
    }

    @Test
    @Description("Check logIn requests with not full and wrong data")
    public void loginWithWrongDataTest() {
        Courier courier = Courier.builder()
                .login(login)
                .password(password)
                .build();
        ValidatableResponse response = courierClient.logIn(courier);
        courierChecks.checkError(response, expectedError);
    }
}
