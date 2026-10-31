package ru.yandex.practicum.filmorate.util;

import lombok.experimental.UtilityClass;
import ru.yandex.practicum.filmorate.exception.ValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class Validator {
    private final static LocalDate EARLIEST_DATE_RELEASE = LocalDate.of(1895, 12, 28);
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void validateFilmRelease(LocalDate releaseDate) {
        if (releaseDate.isBefore(EARLIEST_DATE_RELEASE)) {
            throw new ValidationException("Дата релиза не может быть раньше чем " + releaseDate.format(formatter));
        }
    }
}
