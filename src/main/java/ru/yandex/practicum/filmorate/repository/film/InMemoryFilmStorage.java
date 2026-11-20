package ru.yandex.practicum.filmorate.repository.film;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public void addLike(long filmId, long userId) {
        Film film = films.get(filmId);
        Set<Long> likes = film.getLikes();
        likes.add(userId);
    }

    @Override
    public void removeLike(long filmId, long userId) {
        Film film = films.get(filmId);
        Set<Long> likes = film.getLikes();
        likes.remove(userId);
    }

    @Override
    public List<Film> getMostPopular(long size) {
        return films.values().stream()
                .sorted(Comparator.comparingInt((Film film) -> film.getLikes().size()).reversed())
                .limit(size)
                .collect(Collectors.toList());
    }

    private Long generateId() {
        Long currentMaxId = films.keySet().stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0L);
        return ++currentMaxId;
    }
}
