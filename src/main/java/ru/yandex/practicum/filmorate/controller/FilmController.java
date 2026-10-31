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
    public Film update(@RequestBody Film mewFilm) {
        Long id = mewFilm.getId();
        Film film = films.get(id);

        if (mewFilm.getName() != null) {
            film.setName(mewFilm.getName());
        }

        if (mewFilm.getDescription() != null) {
            film.setDescription(mewFilm.getDescription());
        }

        if (mewFilm.getReleaseDate() != null) {
            film.setReleaseDate(mewFilm.getReleaseDate());
        }

        if (mewFilm.getDuration() != null) {
            film.setDuration(mewFilm.getDuration());
        }

        return film;
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
