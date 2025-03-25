package ru.yandex.practicum.order.positive;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.yandex.practicum.order.OrderChecks;
import ru.yandex.practicum.order.OrderClient;

@DisplayName("Get Orders List Test")
public class GetOrdersListTest {
    private final OrderClient orderClient = new OrderClient();
    private final OrderChecks orderChecks = new OrderChecks();

    @Test
    @Description("Get Orders List Request without filters test")
    public void getOrdersListTest() {
        ValidatableResponse response = orderClient.getOrdersList();
        orderChecks.checkOrderListNotNull(response);
    }
}
