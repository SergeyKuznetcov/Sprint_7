package ru.yandex.practicum.courier;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
@ToString
public class Courier {
    private String login;
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstName;
}
