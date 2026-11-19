package ru.yandex.practicum.filmorate.dto.user;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

/**
 * DTO для ответа по пользователю.
 * Содержит информацию о пользователе, возвращаемую клиенту при GET-запросах.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class UserResponseDto {

    /**
     * Уникальный идентификатор пользователя.
     */
    private Long id;

    /**
     * Адрес электронной почты пользователя.
     */
    private String email;

    /**
     * Логин пользователя.
     */
    private String login;

    /**
     * Имя пользователя.
     */
    private String name;

    /**
     * Дата рождения пользователя.
     */
    private LocalDate birthday;

    /**
     * Список ID пользователей, являющихся друзьями текущего пользователя.
     */
    private Set<Long> friends;
}
