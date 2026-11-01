package ru.yandex.practicum.filmorate.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO для создания нового пользователя.
 *
 * <p>Используется в запросах на создание пользователя. Все поля, кроме name,
 * обязательны для заполнения.</p>
 *
 * @see User
 */
@Getter
@Setter
@NoArgsConstructor
public class NewUserDto {
    /**
     * Электронная почта пользователя. Не может быть null, должна содержать @.
     */
    @Email(message = "электронная почта не может быть пустой и должна содержать символ @")
    @NotNull(message = "email поле должно быть заполнено")
    private String email;

    /**
     * Логин пользователя. Не может быть пустым или состоять только из пробелов,
     * не должен содержать пробелов и табуляции.
     */
    @NotBlank(message = "логин не может быть пустым и содержать пробелы")
    @Pattern(regexp = "^\\S+$", message = "Логин не должен содержать пробелы")
    private String login;

    /**
     * Имя пользователя. Может быть null или пустым.
     */
    private String name;

    /**
     * Дата рождения пользователя в формате yyyy-MM-dd.
     * Не может быть null и не может быть в будущем.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message = "дата рождения не может быть в будущем.")
    @NotNull(message = "Дата рождения должна быть заполнена")
    private LocalDate birthday;
}
