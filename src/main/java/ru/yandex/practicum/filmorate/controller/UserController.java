package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.user.NewUserDto;
import ru.yandex.practicum.filmorate.dto.user.UpdateUserDto;
import ru.yandex.practicum.filmorate.dto.user.UserResponseDto;
import ru.yandex.practicum.filmorate.service.user.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@RequestBody @Valid NewUserDto newUserDto) {
        UserResponseDto userResponseDto = userService.create(newUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> update(@RequestBody @Valid UpdateUserDto updateUserDto) {
        UserResponseDto userResponseDto = userService.update(updateUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<UserResponseDto> userResponseDtoList = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDtoList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable long userId){
        UserResponseDto user = userService.getById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
