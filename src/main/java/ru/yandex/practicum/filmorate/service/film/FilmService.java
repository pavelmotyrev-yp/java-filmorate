package ru.yandex.practicum.filmorate.service.film;

import ru.yandex.practicum.filmorate.dto.film.FilmResponseDto;
import ru.yandex.practicum.filmorate.dto.film.NewFilmDto;
import ru.yandex.practicum.filmorate.dto.film.UpdateFilmDto;

import java.util.List;

public interface FilmService {

    FilmResponseDto save(NewFilmDto newFilmDto);

    FilmResponseDto update(UpdateFilmDto updateFilmDto);

    List<FilmResponseDto> getAll();

    FilmResponseDto getById(long filmId);

    void addLike(long filmId, long userId);

    void removeLike(long filmId, long userId);

    List<FilmResponseDto> getMostPopular(long size);
}
