package ru.yandex.practicum.filmorate.repository.user;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;

import java.util.*;

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

    @Override
    public void addFriend(long userId, long friendId) {
        User user = users.get(userId);
        User friend = users.get(friendId);
        user.getFriends().add(friendId);
        friend.getFriends().add(userId);
    }

    @Override
    public void removeFriend(long userId, long friendId) {
        User user = users.get(userId);
        User friend = users.get(friendId);
        user.getFriends().remove(friendId);
        friend.getFriends().remove(userId);
    }

    @Override
    public List<User> getCommonFriend(long userId, long friendId) {
        return users.values().stream()
                .filter(user -> user.getFriends().contains(userId) && user.getFriends().contains(friendId))
                .toList();
    }

    @Override
    public List<User> getUserFriends(long id) {
        return users.values().stream()
                .filter(user -> user.getId() != id && user.getFriends().contains(id))
                .toList();
    }
}
