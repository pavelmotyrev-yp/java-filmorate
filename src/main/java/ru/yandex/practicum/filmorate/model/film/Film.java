package ru.yandex.practicum.filmorate.model.film;

import lombok.*;

import java.time.LocalDate;

/**
 * Сущность фильма.
 *
 * <p>Представляет информацию о фильме, включая название, описание,
 * дату релиза и продолжительность.</p>
 *
 * @see NewFilmDto
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Film {
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
     * Дата выхода фильма в прокат (pattern = "yyyy-MM-dd").
     */
    private LocalDate releaseDate;

    /**
     * Продолжительность фильма в минутах.
     */
    private Long duration;
}
