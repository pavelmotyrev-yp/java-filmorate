package ru.yandex.practicum.filmorate.repository.user;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserStorage {
    User create(User user);

    User update(long id, User user);

    Optional<User> get(long id);

    Collection<User> getAll();

    boolean isExist(long id);
}
