package ru.yandex.practicum.filmorate.repository.film;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Интерфейс хранилища фильмов.
 * Предоставляет методы для CRUD-операций и управления лайками.
 */
public interface FilmStorage {

    /**
     * Создаёт новый фильм и присваивает ему уникальный идентификатор.
     *
     * @param film фильм для создания
     * @film созданный фильм с присвоенным идентификатором
     */
    Film create(Film film);

    /**
     * Обновляет данные существующего фильма.
     *
     * @param id идентификатор фильма для обновления
     * @param film новые данные фильма
     * @return обновлённый фильм
     */
    Film update(long id, Film film);

    /**
     * Получает фильм по уникальному идентификатору.
     *
     * @param id идентификатор фильма
     * @return Optional с фильмом, если он найден
     */
    Optional<Film> get(long id);

    /**
     * Возвращает коллекцию всех фильмов.
     *
     * @return коллекция всех фильмов
     */
    Collection<Film> getAll();

    /**
     * Проверяет существование фильма по идентификатору.
     *
     * @param id идентификатор фильма
     * @return true, если фильм существует
     */
    boolean isExist(long id);

    /**
     * Добавляет лайк фильму от пользователя.
     *
     * @param filmId идентификатор фильма
     * @param userId идентификатор пользователя
     */
    void addLike(long filmId, long userId);

    /**
     * Удаляет лайк фильма у пользователя.
     *
     * @param filmId идентификатор фильма
     * @param userId идентификатор пользователя
     */
    void removeLike(long filmId, long userId);

    /**
     * Возвращает список наиболее популярных фильмов по количеству лайков.
     *
     * @param size количество возвращаемых фильмов
     * @return список фильмов, отсортированный по убыванию количества лайков
     */
    List<Film> getMostPopular(long size);
}
