package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.dto.film.FilmResponseDto;
import ru.yandex.practicum.filmorate.dto.film.NewFilmDto;
import ru.yandex.practicum.filmorate.dto.film.UpdateFilmDto;
import ru.yandex.practicum.filmorate.dto.user.UserResponseDto;
import ru.yandex.practicum.filmorate.service.film.FilmService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {
    private final FilmService filmService;

    @PostMapping
    public ResponseEntity<FilmResponseDto> save(@RequestBody @Valid NewFilmDto newFilmDto) {
        FilmResponseDto filmResponseDto = filmService.save(newFilmDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmResponseDto);
    }

    @PutMapping
    public ResponseEntity<FilmResponseDto> update(@RequestBody @Valid UpdateFilmDto updateFilmDto) {
        FilmResponseDto filmResponseDto = filmService.update(updateFilmDto);
        return ResponseEntity.status(HttpStatus.OK).body(filmResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<FilmResponseDto>> getAll() {
        List<FilmResponseDto> filmResponseDtoList = filmService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(filmResponseDtoList);
    }

    @GetMapping("/{filmId}")
    public ResponseEntity<FilmResponseDto> getById(@PathVariable long filmId){
        FilmResponseDto film = filmService.getById(filmId);
        return ResponseEntity.status(HttpStatus.OK).body(film);
    }

}
