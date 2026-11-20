package ru.yandex.practicum.filmorate.service.film;

import ru.yandex.practicum.filmorate.dto.film.FilmResponseDto;
import ru.yandex.practicum.filmorate.dto.film.NewFilmDto;
import ru.yandex.practicum.filmorate.dto.film.UpdateFilmDto;
import ru.yandex.practicum.filmorate.exception.NotFoundException;

import java.util.List;

/**
 * Сервис для управления фильмами.
 * Предоставляет методы для создания, обновления, получения фильмов
 * и управления лайками.
 */
public interface FilmService {

    /**
     * Создаёт новый фильм по DTO с новыми данными.
     *
     * @param newFilmDto DTO с данными для создания фильма
     * @return DTO созданного фильма
     */
    FilmResponseDto save(NewFilmDto newFilmDto);

    /**
     * Обновляет данные существующего фильма.
     *
     * @param updateFilmDto DTO с данными для обновления
     * @return DTO обновлённого фильма
     */
    FilmResponseDto update(UpdateFilmDto updateFilmDto);

    /**
     * Возвращает список всех фильмов.
     *
     * @return список DTO всех фильмов
     */
    List<FilmResponseDto> getAll();

    /**
     * Получает фильм по уникальному идентификатору.
     *
     * @param filmId идентификатор фильма
     * @return DTO фильма
     * @throws NotFoundException если фильм не найден
     */
    FilmResponseDto getById(long filmId);

    /**
     * Добавляет лайк фильму от пользователя.
     *
     * @param filmId идентификатор фильма
     * @param userId идентификатор пользователя
     * @throws NotFoundException если фильм или пользователь не найдены
     */
    void addLike(long filmId, long userId);

    /**
     * Удаляет лайк фильма у пользователя.
     *
     * @param filmId идентификатор фильма
     * @param userId идентификатор пользователя
     * @throws NotFoundException если фильм или пользователь не найдены
     */
    void removeLike(long filmId, long userId);

    /**
     * Возвращает список наиболее популярных фильмов по количеству лайков.
     *
     * @param size количество возвращаемых фильмов
     * @return список DTO популярных фильмов
     */
    List<FilmResponseDto> getMostPopular(long size);
}
