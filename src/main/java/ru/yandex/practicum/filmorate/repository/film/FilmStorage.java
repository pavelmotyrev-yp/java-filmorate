package ru.yandex.practicum.filmorate.repository.film;

import ru.yandex.practicum.filmorate.model.film.Film;

import java.util.Collection;
import java.util.Optional;

public interface FilmStorage {
    Film create(Film film);

    Film update(long id, Film newFilm);

    Optional<Film> get(long id);

    Collection<Film> getAll();

    boolean isExist(long id);
}
