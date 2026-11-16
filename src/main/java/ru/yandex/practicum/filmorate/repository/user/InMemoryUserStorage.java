package ru.yandex.practicum.filmorate.repository.user;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.user.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryUserStorage implements UserStorage {
    private Map<Long, User> users = new HashMap<>();

    public User create(User user) {
        Long id = generateId();
        user.setId(id);
        users.put(id, user);
        return user;
    }

    public User update(long id, User user) {
        users.put(id, user);
        return user;
    }

    public Optional<User> get(long id) {
        return Optional.ofNullable(users.get(id));
    }

    public Collection<User> getAll() {
        return users.values();
    }

    public boolean isExist(long id) {
        User user = users.getOrDefault(id, null);
        return user != null;
    }

    private Long generateId() {
        Long currentMaxId = users.keySet().stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0L);
        return ++currentMaxId;
    }
}
