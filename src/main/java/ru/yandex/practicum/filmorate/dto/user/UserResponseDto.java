package ru.yandex.practicum.filmorate.dto.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@EqualsAndHashCode
@ToString
@Builder
public class UserResponseDto {
    private Long id;
    private String email;
    private String login;
    private String name;
    private LocalDate birthday;
    private Set<Long> friends;
}
