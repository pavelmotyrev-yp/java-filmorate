package ru.yandex.practicum.filmorate.model.film;

import lombok.*;

import java.time.LocalDate;

/**
 * Film.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Film {
    private Long id;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private Long duration;
}
