package ru.yandex.practicum.filmorate.model.film;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class NewFilmDto {
    @NotBlank(message = "название не может быть пустым")
    private String name;

    @Size(max = 200, message = "описание должно быть не более 200 символов")
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "дата релиза обязательна к заполнению")
    private LocalDate releaseDate;

    @Positive(message = "продолжительность фильма должна быть положительным числом")
    private Long duration;
}
