package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private Map<Long, User> users = new HashMap<>();

    @PostMapping
    public User create(@RequestBody User user) {
        Long id = generateId();
        user.setId(id);
        users.put(user.getId(), user);
        return user;
    }

    @PutMapping
    public User update(@RequestBody User newUser) {
        Long id = newUser.getId();
        User user = users.get(id);

        if (newUser.getEmail() != null) {
            user.setEmail(newUser.getEmail());
        }

        if (newUser.getLogin() != null) {
            user.setLogin(newUser.getLogin());
        }

        if (newUser.getName() != null) {
            user.setName(newUser.getName());
        }

        if (newUser.getBirthday() != null) {
            user.setBirthday(newUser.getBirthday());
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
