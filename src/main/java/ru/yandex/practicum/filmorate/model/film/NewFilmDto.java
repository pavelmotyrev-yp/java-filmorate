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

/**
 * DTO для создания нового фильма.
 *
 * <p>Используется в запросах на создание фильма. Все поля, кроме description,
 * обязательны для заполнения.</p>
 *
 * @see Film
 */
@Getter
@Setter
@NoArgsConstructor
public class NewFilmDto {
    /**
     * Название фильма. Не может быть пустым или состоять только из пробелов.
     */
    @NotBlank(message = "название не может быть пустым")
    private String name;

    /**
     * Описание фильма. Максимальная длина — 200 символов.
     * Может быть null или пустой строкой.
     */
    @Size(max = 200, message = "описание должно быть не более 200 символов")
    private String description;

    /**
     * Дата выхода фильма в прокат в формате yyyy-MM-dd.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "дата релиза обязательна к заполнению")
    private LocalDate releaseDate;

    /**
     * Продолжительность фильма в минутах.
     * Должна быть положительным числом.
     */
    @Positive(message = "продолжительность фильма должна быть положительным числом")
    @NotNull(message = "продолжительность обязательна к заполнению")
    private Long duration;
}
