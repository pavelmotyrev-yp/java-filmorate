package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
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
public class UserController {
    private Map<Long, User> users = new HashMap<>();

    @PostMapping
    public User create(@RequestBody @Valid NewUserDto newUserDto) {
        Long id = generateId();

        User user = User.builder()
                .id(id)
                .email(newUserDto.getEmail())
                .login(newUserDto.getLogin())
                .name(newUserDto.getName() == null ? newUserDto.getLogin() : newUserDto.getName())
                .birthday(newUserDto.getBirthday())
                .build();

        users.put(id, user);
        return user;
    }

    @PutMapping
    public User update(@RequestBody @Valid UpdateUserDto updateUserDto) {
        Long id = updateUserDto.getId();
        User user = users.getOrDefault(id, null);

        if (user == null) {
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

        return user;
    }

    @GetMapping
    public Collection<User> findAll() {
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
