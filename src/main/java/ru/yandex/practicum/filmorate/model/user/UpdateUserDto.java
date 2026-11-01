package ru.yandex.practicum.filmorate.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserDto {
    @NotNull(message = "id не может быть null")
    @Positive(message = "id должен быть положительным числом")
    private Long id;

    @Email(message = "электронная почта не может быть пустой и должна содержать символ @")
    private String email;

    @Pattern(regexp = "^\\S+$", message = "Логин не должен содержать пробелы")
    private String login;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message = "дата рождения не может быть в будущем.")
    private LocalDate birthday;
}

