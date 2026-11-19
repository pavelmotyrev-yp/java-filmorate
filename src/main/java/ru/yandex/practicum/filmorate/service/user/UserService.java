package ru.yandex.practicum.filmorate.service.user;

import ru.yandex.practicum.filmorate.dto.user.NewUserDto;
import ru.yandex.practicum.filmorate.dto.user.UpdateUserDto;
import ru.yandex.practicum.filmorate.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto create(NewUserDto newUserDto);

    UserResponseDto update(UpdateUserDto updateUserDto);

    List<UserResponseDto> getAll();

    UserResponseDto getById(long userId);

    void addFriend(long userId, long friendId);

    void removeFriend(long userId, long friendId);

    List<UserResponseDto> getCommonFriend(long userId, long friendId);
}
