package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.user.NewUserDto;
import ru.yandex.practicum.filmorate.model.user.UpdateUserDto;
import ru.yandex.practicum.filmorate.model.user.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private Map<Long, User> users = new HashMap<>();

    @PostMapping
    public User create(@RequestBody @Valid NewUserDto newUserDto) {
        log.debug("Создание пользователя, login: {}", newUserDto.getLogin());

        Long id = generateId();

        User user = User.builder()
                .id(id)
                .email(newUserDto.getEmail())
                .login(newUserDto.getLogin())
                .name(newUserDto.getName() == null ? newUserDto.getLogin() : newUserDto.getName())
                .birthday(newUserDto.getBirthday())
                .build();

        users.put(id, user);
        log.info("Пользователь успешно создан, id: {}", id);
        return user;
    }

    @PutMapping
    public User update(@RequestBody @Valid UpdateUserDto updateUserDto) {
        Long id = updateUserDto.getId();
        log.debug("Обновление пользователя, id: {}", id);

        User user = users.getOrDefault(id, null);

        if (user == null) {
            log.warn("Пользователь не найден, id: {}", id);
            throw new NotFoundException("пользователь с id " + id + " не найден");
        }

        if (updateUserDto.getEmail() != null) {
            user.setEmail(updateUserDto.getEmail());
        }

        if (updateUserDto.getLogin() != null) {
            user.setLogin(updateUserDto.getLogin());
        }

        if (updateUserDto.getName() != null) {
            user.setName(updateUserDto.getName());
        }

        if (updateUserDto.getBirthday() != null) {
            user.setBirthday(updateUserDto.getBirthday());
        }

        log.info("Пользователь успешно обновлён, id: {}", id);
        return user;
    }

    @GetMapping
    public Collection<User> getAll() {
        log.debug("Запрос списка пользователей, кол-во: {}", users.size());
        return users.values();
    }

    private Long generateId() {
        Long currentMaxId = users.keySet().stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0L);
        return ++currentMaxId;
    }
}
