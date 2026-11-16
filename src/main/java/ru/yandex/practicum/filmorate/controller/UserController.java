package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.user.NewUserDto;
import ru.yandex.practicum.filmorate.dto.user.UpdateUserDto;
import ru.yandex.practicum.filmorate.model.User;

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
        return null;
    }

    @PutMapping
    public User update(@RequestBody @Valid UpdateUserDto updateUserDto) {
        return null;
    }

    @GetMapping
    public Collection<User> getAll() {
        return null;
    }
}
