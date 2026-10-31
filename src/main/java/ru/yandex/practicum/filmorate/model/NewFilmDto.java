package ru.yandex.practicum.filmorate.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class NewFilmDto {
    @NotBlank(message = "название не может быть пустым")
    private String name;

    @Size(max = 200, message = "описание должно быть не более 200 символов")
    private String description;

    private Instant releaseDate;

    @Positive(message = "продолжительность фильма должна быть положительным числом")
    private Long duration;
}
