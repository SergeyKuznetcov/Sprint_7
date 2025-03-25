package ru.yandex.practicum.order.positive;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.constants.Constants;
import ru.yandex.practicum.order.Order;
import ru.yandex.practicum.order.OrderChecks;
import ru.yandex.practicum.order.OrderClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static ru.yandex.practicum.constants.Colours.BLACK;
import static ru.yandex.practicum.constants.Colours.GREY;

@RunWith(Parameterized.class)
@DisplayName("Create Order Tests")
public class CreateOrderTests {
    private final OrderClient orderClient = new OrderClient();
    private final OrderChecks orderChecks = new OrderChecks();
    Integer orderId;

    @Parameterized.Parameter
    public Order order;

    @Parameterized.Parameters(name = " {index}: order = {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {getRandomizedOrder(List.of(BLACK.name(), GREY.name()))},
                {getRandomizedOrder(List.of(GREY.name()))},
                {getRandomizedOrder(List.of(BLACK.name()))},
                {getRandomizedOrder(List.of())}
        });
    }

    @Test
    @Description("Create orders with different colours")
    public void createOrderTest() {
        ValidatableResponse response = orderClient.createOrder(order);
        orderId = orderChecks.createOrderCheck(response);
    }

    @After
    public void tearDown() {
        if (orderId != null) {
            orderChecks.checkCanceled(orderClient.cancelOrder(orderId));
            orderId = null;
        }
    }

    private static Order getRandomizedOrder(List<String> colours) {
        Random random = new Random();
        return Order.builder()
                .firstName("Name")
                .lastName("Last Name")
                .address("Address example")
                .metroStation(random.nextInt(Constants.METRO_STATION_COUNT) + 1)
                .phone("+7 xxx xxx xx xx")
                .rentTime(random.nextInt(Constants.RENT_DURATION_LIMIT))
                .comment("Comment example")
                .deliveryDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .color(colours)
                .build();
    }
}
