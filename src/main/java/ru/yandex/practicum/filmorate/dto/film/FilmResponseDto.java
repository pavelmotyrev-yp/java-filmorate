package ru.yandex.practicum.filmorate.dto.film;

import lombok.*;

import java.time.LocalDate;

/**
 * DTO для ответа по фильму.
 * Содержит информацию о фильме, возвращаемую клиенту при GET-запросах.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class FilmResponseDto {

    /**
     * Уникальный идентификатор фильма.
     */
    private Long id;

    /**
     * Название фильма.
     */
    private String name;

    /**
     * Описание фильма.
     */
    private String description;

    /**
     * Дата выхода фильма.
     */
    private LocalDate releaseDate;

    /**
     * Длительность фильма в минутах.
     */
    private Long duration;
}
