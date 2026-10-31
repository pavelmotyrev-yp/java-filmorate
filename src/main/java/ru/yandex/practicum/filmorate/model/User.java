package ru.yandex.practicum.filmorate.model;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@Builder
public class User {
    private Long id;
    private String email;
    private String login;
    private String name;
    private Instant birthday;
}
