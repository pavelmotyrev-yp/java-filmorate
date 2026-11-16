package ru.yandex.practicum.filmorate.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO для обновления информации о пользователе.
 *
 * <p>Используется в запросах на обновление существующего пользователя.
 * Поле id обязательно. Остальные поля необязательны — обновляются
 * только те, которые переданы в запросе.</p>
 *
 * @see User
 */
@Getter
@Setter
@NoArgsConstructor
public class UpdateUserDto {
    /**
     * Идентификатор пользователя. Не может быть null и должен быть положительным.
     */
    @NotNull(message = "id не может быть null")
    @Positive(message = "id должен быть положительным числом")
    private Long id;

    /**
     * Новый адрес электронной почты. Должен содержать @, если передан.
     */
    @Email(message = "электронная почта не может быть пустой и должна содержать символ @")
    private String email;

    /**
     * Новый логин пользователя. Не должен содержать пробелов и табуляции.
     */
    @Pattern(regexp = "^\\S+$", message = "Логин не должен содержать пробелы")
    private String login;

    /**
     * Новое имя пользователя. Может быть null.
     */
    private String name;

    /**
     * Новая дата рождения в формате yyyy-MM-dd.
     * Не может быть в будущем, если передана.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message = "дата рождения не может быть в будущем.")
    private LocalDate birthday;
}

