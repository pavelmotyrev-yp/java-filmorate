package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.film.Film;
import ru.yandex.practicum.filmorate.model.film.NewFilmDto;
import ru.yandex.practicum.filmorate.model.film.UpdateFilmDto;
import ru.yandex.practicum.filmorate.util.Validator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private Map<Long, Film> films = new HashMap<>();

    @PostMapping
    public Film save(@RequestBody @Valid NewFilmDto newFilmDto) {
        log.debug("Создание фильма, name: {}", newFilmDto.getName());

        Long id = generateId();

        Validator.validateFilmRelease(newFilmDto.getReleaseDate());

        Film film = Film.builder()
                .id(id)
                .name(newFilmDto.getName())
                .description(newFilmDto.getDescription())
                .releaseDate(newFilmDto.getReleaseDate())
                .duration(newFilmDto.getDuration())
                .build();

        films.put(id, film);
        log.info("Фильм успешно создан, id: {}", id);
        return film;
    }

    @PutMapping
    public Film update(@RequestBody @Valid UpdateFilmDto updateFilmDto) {
        Long id = updateFilmDto.getId();
        log.debug("Обновление фильма, id: {}, name: {}", id, updateFilmDto.getName());

        Film film = films.getOrDefault(id, null);

        if (film == null) {
            log.warn("Фильм не найден, id: {}", id);
            throw new NotFoundException("фильм с id " + id + " не найден");
        }

        if (updateFilmDto.getName() != null) {
            film.setName(updateFilmDto.getName());
        }

        if (updateFilmDto.getDescription() != null) {
            film.setDescription(updateFilmDto.getDescription());
        }

        if (updateFilmDto.getReleaseDate() != null) {
            Validator.validateFilmRelease(updateFilmDto.getReleaseDate());
            film.setReleaseDate(updateFilmDto.getReleaseDate());
        }

        if (updateFilmDto.getDuration() != null) {
            film.setDuration(updateFilmDto.getDuration());
        }

        log.info("Фильм успешно обновлён, id: {}", id);
        return film;
    }

    @GetMapping
    public Collection<Film> getAll() {
        log.debug("Запрос списка фильмов, кол-во: {}", films.size());
        return films.values();
    }

    private Long generateId() {
        Long currentMaxId = films.keySet().stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0L);
        return ++currentMaxId;
    }
}
