package ru.yandex.practicum.filmorate.repository.film;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.film.Film;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class InMemoryFilmStorage implements FilmStorage {
    private Map<Long, Film> films = new HashMap<>();

    public Film create(Film film) {
        Long id = generateId();
        film.setId(id);
        films.put(id, film);
        return film;
    }

    public Film update(long id, Film film) {
        films.put(id, film);
        return film;
    }

    public Optional<Film> get(long id) {
        return Optional.ofNullable(films.get(id));
    }

    public Collection<Film> getAll() {
        return films.values();
    }

    public boolean isExist(long id) {
        Film film = films.getOrDefault(id, null);
        return film != null;
    }

    private Long generateId() {
        Long currentMaxId = films.keySet().stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0L);
        return ++currentMaxId;
    }
}
