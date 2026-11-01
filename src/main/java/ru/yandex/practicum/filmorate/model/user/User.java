package ru.yandex.practicum.filmorate.model.user;

import lombok.*;

import java.time.LocalDate;

/**
 * Сущность пользователя.
 *
 * <p>Представляет информацию о пользователе, включая email, логин,
 * имя и дату рождения.</p>
 *
 * @see NewUserDto
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class User {
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
}
