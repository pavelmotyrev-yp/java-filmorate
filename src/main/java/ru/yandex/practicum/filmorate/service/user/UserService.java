package ru.yandex.practicum.filmorate.service.user;

import ru.yandex.practicum.filmorate.dto.user.NewUserDto;
import ru.yandex.practicum.filmorate.dto.user.UpdateUserDto;
import ru.yandex.practicum.filmorate.dto.user.UserResponseDto;
import ru.yandex.practicum.filmorate.exception.NotFoundException;

import java.util.List;

/**
 * Сервис для управления пользователями.
 * Предоставляет методы для создания, обновления, получения пользователей
 * и управления дружбой.
 */
public interface UserService {

    /**
     * Создаёт нового пользователя по DTO с новыми данными.
     *
     * @param newUserDto DTO с данными для создания пользователя
     * @return DTO созданного пользователя
     */
    UserResponseDto create(NewUserDto newUserDto);

    /**
     * Обновляет данные существующего пользователя.
     *
     * @param updateUserDto DTO с данными для обновления
     * @return DTO обновлённого пользователя
     * @throws NotFoundException если пользователь не найден
     */
    UserResponseDto update(UpdateUserDto updateUserDto);

    /**
     * Возвращает список всех пользователей.
     *
     * @return список DTO всех пользователей
     */
    List<UserResponseDto> getAll();

    /**
     * Получает пользователя по уникальному идентификатору.
     *
     * @param userId идентификатор пользователя
     * @return DTO пользователя
     * @throws NotFoundException если пользователь не найден
     */
    UserResponseDto getById(long userId);

    /**
     * Добавляет пользователя в друзья другому пользователю.
     * Дружба двунаправленная — оба пользователя получают друг друга в список друзей.
     *
     * @param userId идентификатор пользователя
     * @param friendId идентификатор пользователя, которого нужно добавить в друзья
     * @throws NotFoundException если пользователь или друг не найдены
     */
    void addFriend(long userId, long friendId);

    /**
     * Удаляет пользователя из списка друзей.
     * Удаление двунаправленное — оба пользователя удаляются из списков друзей друг друга.
     *
     * @param userId идентификатор пользователя
     * @param friendId идентификатор пользователя, которого нужно удалить из друзей
     * @throws NotFoundException если пользователь или друг не найдены
     */
    void removeFriend(long userId, long friendId);

    /**
     * Возвращает список общих друзей двух пользователей.
     *
     * @param userId идентификатор первого пользователя
     * @param friendId идентификатор второго пользователя
     * @return список DTO пользователей, которые являются друзьями обоих
     * @throws NotFoundException если пользователь не найден
     */
    List<UserResponseDto> getCommonFriend(long userId, long friendId);

    /**
     * Возвращает список друзей пользователя.
     *
     * @param id идентификатор пользователя
     * @return список DTO друзей пользователя
     * @throws NotFoundException если пользователь не найден
     */
    List<UserResponseDto> getUserFriends(long id);
}
