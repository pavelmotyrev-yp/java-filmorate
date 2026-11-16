package ru.yandex.practicum.filmorate.dto.film;

import lombok.experimental.UtilityClass;
import ru.yandex.practicum.filmorate.model.Film;

@UtilityClass
public class FilmMapper {

    public static Film toFilm(NewFilmDto newFilm) {
        return Film.builder()
                .name(newFilm.getName())
                .description(newFilm.getDescription())
                .releaseDate(newFilm.getReleaseDate())
                .duration(newFilm.getDuration())
                .build();
    }

    public static Film toFilm(Film film, UpdateFilmDto updateFilm) {
        if (updateFilm.getName() != null) {
            film.setName(updateFilm.getName());
        }
        if (updateFilm.getDescription() != null) {
            film.setDescription(updateFilm.getDescription());
        }
        if (updateFilm.getReleaseDate() != null) {
            film.setReleaseDate(updateFilm.getReleaseDate());
        }
        if (updateFilm.getDuration() != null) {
            film.setDuration(updateFilm.getDuration());
        }
        return film;
    }

    public static FilmResponseDto toFilmResponseDto(Film film) {
        return FilmResponseDto.builder()
                .id(film.getId())
                .name(film.getName())
                .description(film.getDescription())
                .releaseDate(film.getReleaseDate())
                .duration(film.getDuration())
                .build();
    }
}
