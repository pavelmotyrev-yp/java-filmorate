package ru.yandex.practicum.filmorate.model.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class NewUserDto {
    @Email(message = "электронная почта не может быть пустой и должна содержать символ @")
    private String email;

    @NotBlank(message = "электронная почта не может быть пустой и должна содержать символ @")
    @Pattern(regexp = "^\\S+$", message = "Логин не должен содержать пробелы")
    private String login;

    private String name;

    @Past(message = "дата рождения не может быть в будущем.")
    private LocalDate birthday;
}
