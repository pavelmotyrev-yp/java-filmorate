package ru.yandex.practicum.filmorate.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exception.ValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@UtilityClass
public class Validator {
    private static final LocalDate EARLIEST_DATE_RELEASE = LocalDate.of(1895, 12, 28);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void validateFilmRelease(LocalDate releaseDate) {
        if (releaseDate.isBefore(EARLIEST_DATE_RELEASE)) {
            log.warn("Дата релиза не может быть раньше {}", EARLIEST_DATE_RELEASE.format(formatter));
            throw new ValidationException("Дата релиза не может быть раньше чем " + releaseDate.format(formatter));
        }
        log.debug("Валидация даты релиза пройдена, releaseDate: {}", releaseDate);
    }
}
