package ru.yandex.practicum.filmorate.model;

import lombok.*;

import java.time.Instant;

/**
 * Film.
 */
@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@Builder
public class Film {
    private final long id;
    private final String name;
    private final String description;
    private final Instant releaseDate;
    private final long duration;
}
