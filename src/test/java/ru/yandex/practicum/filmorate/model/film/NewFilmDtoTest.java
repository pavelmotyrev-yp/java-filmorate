package ru.yandex.practicum.filmorate.model.film;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NewFilmDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void should_pass_with_valid_data() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("Test Film");
        dto.setDescription("A great movie");
        dto.setReleaseDate(LocalDate.of(2020, 5, 15));
        dto.setDuration(120L);

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений при корректных данных");
    }

    @Test
    void should_fail_when_name_is_null() {
        NewFilmDto dto = new NewFilmDto();
        dto.setDescription("A great movie");
        dto.setReleaseDate(LocalDate.of(2020, 5, 15));
        dto.setDuration(120L);

        var violations = validator.validate(dto);
        
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("название не может быть пустым")));
    }

    @Test
    void should_fail_when_name_is_empty() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("");
        dto.setDescription("A great movie");
        dto.setReleaseDate(LocalDate.of(2020, 5, 15));
        dto.setDuration(120L);

        var violations = validator.validate(dto);
        
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("название не может быть пустым")));
    }

    @Test
    void should_fail_when_name_is_whitespace() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("   ");
        dto.setDescription("A great movie");
        dto.setReleaseDate(LocalDate.of(2020, 5, 15));
        dto.setDuration(120L);

        var violations = validator.validate(dto);
        
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("название не может быть пустым")));
    }

    @Test
    void should_pass_with_non_empty_name() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("A");
        dto.setDescription("A great movie");
        dto.setReleaseDate(LocalDate.of(2020, 5, 15));
        dto.setDuration(120L);

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для имени из одного символа");
    }

    @Test
    void should_pass_when_description_is_null() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("Test Film");
        dto.setReleaseDate(LocalDate.of(2020, 5, 15));
        dto.setDuration(120L);

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для null описания");
    }

    @Test
    void should_pass_when_description_is_empty() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("Test Film");
        dto.setDescription("");
        dto.setReleaseDate(LocalDate.of(2020, 5, 15));
        dto.setDuration(120L);

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для пустого описания");
    }

    @Test
    void should_pass_when_description_is_exactly_200_chars() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("Test Film");
        dto.setDescription("a".repeat(200));
        dto.setReleaseDate(LocalDate.of(2020, 5, 15));
        dto.setDuration(120L);

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для описания ровно 200 символов");
    }

    @Test
    void should_fail_when_description_is_201_chars() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("Test Film");
        dto.setDescription("a".repeat(201));
        dto.setReleaseDate(LocalDate.of(2020, 5, 15));
        dto.setDuration(120L);

        var violations = validator.validate(dto);
        
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("description")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("описание должно быть не более 200 символов")));
    }

    @Test
    void should_fail_when_description_is_1000_chars() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("Test Film");
        dto.setDescription("a".repeat(1000));
        dto.setReleaseDate(LocalDate.of(2020, 5, 15));
        dto.setDuration(120L);

        var violations = validator.validate(dto);
        
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("description")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("описание должно быть не более 200 символов")));
    }

    @Test
    void should_fail_when_release_date_is_null() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("Test Film");
        dto.setDescription("A great movie");
        dto.setDuration(120L);

        var violations = validator.validate(dto);
        
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("releaseDate")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("дата релиза обязательна к заполнению")));
    }

    @Test
    void should_pass_when_release_date_is_in_past() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("Test Film");
        dto.setDescription("A great movie");
        dto.setReleaseDate(LocalDate.of(2020, 5, 15));
        dto.setDuration(120L);

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для даты в прошлом");
    }

    @Test
    void should_pass_when_release_date_is_today() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("Test Film");
        dto.setDescription("A great movie");
        dto.setReleaseDate(LocalDate.now());
        dto.setDuration(120L);

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для сегодняшней даты");
    }

    @Test
    void should_pass_when_release_date_is_in_future() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("Test Film");
        dto.setDescription("A great movie");
        dto.setReleaseDate(LocalDate.now().plusDays(1));
        dto.setDuration(120L);

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для даты в будущем");
    }

    @Test
    void should_fail_when_duration_is_null() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("Test Film");
        dto.setDescription("A great movie");
        dto.setReleaseDate(LocalDate.of(2020, 5, 15));

        var violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("duration")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("продолжительность обязательна к заполнению")));
    }

    @Test
    void should_fail_when_duration_is_zero() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("Test Film");
        dto.setDescription("A great movie");
        dto.setReleaseDate(LocalDate.of(2020, 5, 15));
        dto.setDuration(0L);

        var violations = validator.validate(dto);
        
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("duration")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("продолжительность фильма должна быть положительным числом")));
    }

    @Test
    void should_fail_when_duration_is_negative() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("Test Film");
        dto.setDescription("A great movie");
        dto.setReleaseDate(LocalDate.of(2020, 5, 15));
        dto.setDuration(-1L);

        var violations = validator.validate(dto);
        
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("duration")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("продолжительность фильма должна быть положительным числом")));
    }

    @Test
    void should_pass_when_duration_is_one() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("Test Film");
        dto.setDescription("A great movie");
        dto.setReleaseDate(LocalDate.of(2020, 5, 15));
        dto.setDuration(1L);

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для продолжительности равной 1");
    }

    @Test
    void should_pass_when_duration_is_large() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("Test Film");
        dto.setDescription("A great movie");
        dto.setReleaseDate(LocalDate.of(2020, 5, 15));
        dto.setDuration(999999L);

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для большой продолжительности");
    }

    @Test
    void should_report_all_violations() {
        NewFilmDto dto = new NewFilmDto();
        dto.setName("");
        dto.setDescription("a".repeat(300));
        dto.setReleaseDate(null);
        dto.setDuration(-5L);

        var violations = validator.validate(dto);
        assertEquals(4, violations.size(), "Ожидается 4 исключения при некорректных данных");
    }
}
