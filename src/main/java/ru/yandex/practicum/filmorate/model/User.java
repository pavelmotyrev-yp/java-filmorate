package ru.yandex.practicum.filmorate.model;

import lombok.*;

import java.time.Instant;

@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@Builder
public class User {
    private final long id;
    private final String email;
    private final String login;
    private final String name;
    private final Instant birthday;
}
