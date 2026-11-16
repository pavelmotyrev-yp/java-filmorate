package ru.yandex.practicum.filmorate.model;

import lombok.*;
import ru.yandex.practicum.filmorate.dto.film.NewFilmDto;

import java.time.LocalDate;
import java.util.Set;

/**
 * Сущность фильма.
 *
 * <p>Представляет информацию о фильме, включая название, описание,
 * дату релиза, продолжительность и количество лайков от пользователей.</p>
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

    /**
     * Лайки от пользователей. При добавлении лайка от пользователя сохраняется его ID.
     */
    private Set<Long> likes;
}
