package ru.yandex.practicum.filmorate.model.film;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO для обновления информации о фильме.
 *
 * <p>Используется в запросах на обновление существующего фильма.
 * Поле id обязательно. Остальные поля необязательны — обновляются
 * только те, которые переданы в запросе.</p>
 *
 * @see Film
 */
@Getter
@Setter
@NoArgsConstructor
public class UpdateFilmDto {
    /**
     * Идентификатор фильма. Не может быть null и должен быть положительным.
     */
    @NotNull(message = "id не может быть null")
    @Positive(message = "id должен быть положительным числом")
    private Long id;

    /**
     * Новое название фильма. Не может быть пустым.
     */
    @NotEmpty
    private String name;

    /**
     * Новое описание фильма. Максимальная длина — 200 символов.
     * Может быть null.
     */
    @Size(max = 200, message = "описание должно быть не более 200 символов")
    private String description;

    /**
     * Новая дата выхода фильма в формате yyyy-MM-dd.
     * Может быть null.
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    /**
     * Новая продолжительность фильма в минутах.
     * Должна быть положительным числом, если передана.
     */
    @Positive(message = "продолжительность фильма должна быть положительным числом")
    private Long duration;
}
