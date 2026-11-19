package ru.yandex.practicum.filmorate.dto.user;

import lombok.experimental.UtilityClass;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashSet;

@UtilityClass
public class UserMapper {
    public static User toUser(NewUserDto newUser) {
        return User.builder()
                .email(newUser.getEmail())
                .login(newUser.getLogin())
                .name(newUser.getName() == null ? newUser.getLogin() : newUser.getName())
                .birthday(newUser.getBirthday())
                .friends(new HashSet<>())
                .build();
    }

    public static User toUser(User user, UpdateUserDto updateUser) {
        if (updateUser.getEmail() != null) {
            user.setEmail(updateUser.getEmail());
        }
        if (updateUser.getLogin() != null) {
            user.setLogin(updateUser.getLogin());
        }
        if (updateUser.getName() != null) {
            user.setName(updateUser.getName());
        }
        if (updateUser.getBirthday() != null) {
            user.setBirthday(updateUser.getBirthday());
        }
        return user;
    }

    public static UserResponseDto toUserResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .login(user.getLogin())
                .name(user.getName())
                .birthday(user.getBirthday())
                .friends(user.getFriends())
                .build();
    }
}
