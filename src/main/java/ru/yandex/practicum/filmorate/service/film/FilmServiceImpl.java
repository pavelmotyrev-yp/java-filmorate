package ru.yandex.practicum.filmorate.service.film;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dto.film.FilmMapper;
import ru.yandex.practicum.filmorate.dto.film.FilmResponseDto;
import ru.yandex.practicum.filmorate.dto.film.NewFilmDto;
import ru.yandex.practicum.filmorate.dto.film.UpdateFilmDto;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.repository.film.FilmStorage;
import ru.yandex.practicum.filmorate.repository.user.UserStorage;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmStorage filmStorage;
    private final UserStorage userStorage;

    @Override
    public FilmResponseDto save(NewFilmDto newFilmDto) {
        log.info("Создание фильма, name: {}", newFilmDto.getName());
        Film film = FilmMapper.toFilm(newFilmDto);
        Film savedFilm = filmStorage.create(film);
        log.info("Фильм сохранен с ID: {}", savedFilm.getId());
        return FilmMapper.toFilmResponseDto(savedFilm);
    }

    @Override
    public FilmResponseDto update(UpdateFilmDto updateFilmDto) {
        log.info("Обновление вильма по ID={}", updateFilmDto.getId());
        Optional<Film> filmOptional = filmStorage.get(updateFilmDto.getId());
        Film film = filmOptional.orElseThrow(() -> new NotFoundException("Не найден фильм по ID=" + updateFilmDto.getId()));
        Film updatedFilm = FilmMapper.toFilm(film, updateFilmDto);
        Film savedFilm = filmStorage.update(updatedFilm.getId(), updatedFilm);
        log.info("Фильм обновлен по ID: {}", savedFilm.getId());
        return FilmMapper.toFilmResponseDto(savedFilm);
    }

    @Override
    public List<FilmResponseDto> getAll() {
        log.info("Получение списка фильмов");
        Collection<Film> films = filmStorage.getAll();
        log.info("Список фильмов получен size={}", films.size());
        return films.stream()
                .map(FilmMapper::toFilmResponseDto)
                .toList();
    }

    @Override
    public FilmResponseDto getById(long filmId) {
        log.info("Получение фильма по ID={}", filmId);
        Optional<Film> optionalFilm = filmStorage.get(filmId);
        Film film = optionalFilm.orElseThrow(() -> new NotFoundException("Фильм не найден по ID=" + filmId));
        log.info("Пользователь получен по ID={}", filmId);
        return FilmMapper.toFilmResponseDto(film);
    }

    @Override
    public void addLike(long filmId, long userId) {
        log.info("Добавление лайка filmId={} от userId={}", filmId, userId);
        checkUserId(userId);
        checkFilmId(filmId);
        filmStorage.addLike(filmId, userId);
        log.info("Лайк filmId={} от userId={} добавлен", filmId, userId);
    }

    @Override
    public void removeLike(long filmId, long userId) {
        log.info("Удаление лайка filmId={} от userId={}", filmId, userId);
        checkUserId(userId);
        checkFilmId(filmId);
        filmStorage.removeLike(filmId, userId);
        log.info("Лайк filmId={} от userId={} удален", filmId, userId);
    }

    @Override
    public List<FilmResponseDto> getMostPopular(long size) {
        log.info("Получение {} самых популярных фильмов", size);
        List<Film> films = filmStorage.getMostPopular(size);
        log.info("Получено {} самых популярных фильмов", films.size());
        return films.stream()
                .map(FilmMapper::toFilmResponseDto)
                .toList();
    }

    private void checkFilmId(long filmId) {
        if (!filmStorage.isExist(filmId)) {
            throw new NotFoundException("Фильм не найден по ID=" + filmId);
        }
    }

    private void checkUserId(long userId) {
        if (!userStorage.isExist(userId)) {
            throw new NotFoundException("Фильм не найден по ID=" + userId);
        }
    }
}
