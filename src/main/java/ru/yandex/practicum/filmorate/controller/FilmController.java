package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/films")
public class FilmController {
    private Map<Long, Film> films = new HashMap<>();

    @PostMapping
    public Film save(@RequestBody Film film) {
        Long id = generateId();
        film.setId(id);
        films.put(id, film);
        return film;
    }

    @PutMapping
    public Film update(@RequestBody Film film) {
        Long id = film.getId();
        Film filmFromDb = films.get(id);

        if (film.getName() != null) {
            filmFromDb.setName(film.getName());
        }

        if (film.getDescription() != null) {
            filmFromDb.setDescription(film.getDescription());
        }

        if (film.getReleaseDate() != null) {
            filmFromDb.setReleaseDate(film.getReleaseDate());
        }

        if (film.getDuration() != null) {
            filmFromDb.setDuration(film.getDuration());
        }

        return filmFromDb;
    }

    @GetMapping
    public Collection<Film> getAll() {
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
