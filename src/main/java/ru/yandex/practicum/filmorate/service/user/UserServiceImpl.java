package ru.yandex.practicum.filmorate.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dto.user.NewUserDto;
import ru.yandex.practicum.filmorate.dto.user.UpdateUserDto;
import ru.yandex.practicum.filmorate.dto.user.UserMapper;
import ru.yandex.practicum.filmorate.dto.user.UserResponseDto;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.repository.user.UserStorage;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserStorage userStorage;

    @Override
    public UserResponseDto create(NewUserDto newUserDto) {
        log.info("Создание нового пользователя");
        User user = UserMapper.toUser(newUserDto);
        User savedUser = userStorage.create(user);
        log.debug("Пользователь сохранен с ID={}", savedUser.getId());
        return UserMapper.toUserResponseDto(savedUser);
    }

    @Override
    public UserResponseDto update(UpdateUserDto updateUserDto) {
        log.info("Обновление пользователя по ID={}", updateUserDto.getId());
        Optional<User> userOptional = userStorage.get(updateUserDto.getId());
        User user = userOptional.orElseThrow(() -> new NotFoundException("Пользователь не найден по ID=" + updateUserDto.getId()));
        User updatedUser = UserMapper.toUser(user, updateUserDto);
        User savedUser = userStorage.update(updatedUser.getId(), updatedUser);
        log.debug("Пользователь обновлен по ID={}", savedUser.getId());
        return UserMapper.toUserResponseDto(savedUser);
    }

    @Override
    public List<UserResponseDto> getAll() {
        log.info("Получение всех пользователей");
        Collection<User> userList = userStorage.getAll();
        log.debug("Получен список пользователей размером size={}", userList.size());
        return userList.stream()
                .map(UserMapper::toUserResponseDto)
                .toList();
    }

    @Override
    public void addFriend(long userId, long friendId) {
        log.info("Добавление пользователю с ID={} в друзья пользователя с ID={}", userId, friendId);
        checkUser(userId);
        checkUser(friendId);
        userStorage.addFriend(userId, friendId);
        log.debug("Добавлен пользователю с ID={} в друзья пользователь с ID={}", userId, friendId);
    }

    @Override
    public void removeFriend(long userId, long friendId) {
        log.info("Удаление у пользователя с ID={} из друзей пользователя с ID={}", userId, friendId);
        checkUser(userId);
        checkUser(friendId);
        userStorage.removeFriend(userId, friendId);
        log.debug("Удален у пользователя с ID={} из друзей пользователь с ID={}", userId, friendId);
    }

    @Override
    public List<UserResponseDto> getCommonFriend(long userId, long friendId) {
        log.info("Запрос на список общих друзей пользователя с ID={} и пользователя с ID={}", userId, friendId);
        checkUser(userId);
        checkUser(friendId);
        List<User> commonFriendList = userStorage.getCommonFriend(userId, friendId);
        log.debug("Список общих друзей получен size={}", commonFriendList.size());
        return commonFriendList.stream()
                .map(UserMapper::toUserResponseDto)
                .toList();
    }

    private void checkUser(long userId) {
        if (!userStorage.isExist(userId)) {
            throw new NotFoundException("Пользователь не найден по ID=" + userId);
        }
    }
}