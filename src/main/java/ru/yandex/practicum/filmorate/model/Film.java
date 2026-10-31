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
@RequiredArgsConstructor
@Builder
public class Film {
    private Long id;
    private String name;
    private String description;
    private Instant releaseDate;
    private Long duration;
}
