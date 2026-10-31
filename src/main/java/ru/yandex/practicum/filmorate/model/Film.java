package ru.yandex.practicum.filmorate.model;

import lombok.*;

import java.time.Instant;

/**
 * Film.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class Film {
    private long id;
    private String name;
    private String description;
    private Instant releaseDate;
    private long duration;
}
