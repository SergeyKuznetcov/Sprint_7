package ru.yandex.practicum.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.net.HttpURLConnection.*;

@RequiredArgsConstructor
@Getter
public enum Error {
    LOGIN_NOT_ENOUGH_DATA(HTTP_BAD_REQUEST, "Недостаточно данных для входа"),
    LOGIN_NON_EXISTENT_DATA(HTTP_NOT_FOUND, "Учетная запись не найдена"),
    CREATE_COURIER_NOT_ENOUGH_DATA(HTTP_BAD_REQUEST, "Недостаточно данных для создания учетной записи"),
    CREATE_COURIER_DUPLICATED_DATA(HTTP_CONFLICT, "Этот логин уже используется. Попробуйте другой.");

    private final int statusCode;
    private final String message;
}
