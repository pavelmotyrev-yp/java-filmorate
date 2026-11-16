package ru.yandex.practicum.filmorate.model.user;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NewUserDtoTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void should_pass_with_valid_data() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@example.com");
        dto.setLogin("username");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.of(1990, 5, 15));

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений при корректных данных");
    }

    @Test
    void should_fail_when_email_is_null() {
        NewUserDto dto = new NewUserDto();
        dto.setLogin("username");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.of(1990, 5, 15));

        var violations = validator.validate(dto);

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("email поле должно быть заполнено")));
    }

    @Test
    void should_fail_when_email_has_no_at_symbol() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("userexample.com");
        dto.setLogin("username");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.of(1990, 5, 15));

        var violations = validator.validate(dto);

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("электронная почта не может быть пустой и должна содержать символ @")));
    }

    @Test
    void should_fail_when_email_has_no_domain() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@");
        dto.setLogin("username");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.of(1990, 5, 15));

        var violations = validator.validate(dto);

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("электронная почта не может быть пустой и должна содержать символ @")));
    }

    @Test
    void should_pass_with_valid_email() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@example.com");
        dto.setLogin("username");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.of(1990, 5, 15));

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для корректного email");
    }

    @Test
    void should_pass_with_email_with_dots_and_digits() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user.name123@mail.example.com");
        dto.setLogin("username");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.of(1990, 5, 15));

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для сложного email");
    }

    // ==================== LOGIN (@NotBlank + @Pattern) ====================

    @Test
    void should_fail_when_login_is_null() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@example.com");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.of(1990, 5, 15));

        var violations = validator.validate(dto);

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("login")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("логин не может быть пустым и содержать пробелы")));
    }

    @Test
    void should_fail_when_login_is_empty() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@example.com");
        dto.setLogin("");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.of(1990, 5, 15));

        var violations = validator.validate(dto);

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("login")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("логин не может быть пустым и содержать пробелы")));
    }

    @Test
    void should_fail_when_login_is_whitespace() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@example.com");
        dto.setLogin("   ");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.of(1990, 5, 15));

        var violations = validator.validate(dto);

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("login")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("логин не может быть пустым и содержать пробелы")));
    }

    @Test
    void should_fail_when_login_contains_space() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@example.com");
        dto.setLogin("user name");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.of(1990, 5, 15));

        var violations = validator.validate(dto);

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("login")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("Логин не должен содержать пробелы")));
    }

    @Test
    void should_fail_when_login_contains_tab() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@example.com");
        dto.setLogin("user\tname");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.of(1990, 5, 15));

        var violations = validator.validate(dto);

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("login")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("Логин не должен содержать пробелы")));
    }

    @Test
    void should_pass_with_simple_login() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@example.com");
        dto.setLogin("username");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.of(1990, 5, 15));

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для простого логина");
    }

    @Test
    void should_pass_with_login_with_underscores_and_digits() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@example.com");
        dto.setLogin("user_name_123");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.of(1990, 5, 15));

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для логина с символами");
    }

    @Test
    void should_pass_with_single_char_login() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@example.com");
        dto.setLogin("u");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.of(1990, 5, 15));

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для логина из одного символа");
    }


    @Test
    void should_pass_when_name_is_null() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@example.com");
        dto.setLogin("username");
        dto.setBirthday(LocalDate.of(1990, 5, 15));

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для null имени");
    }

    @Test
    void should_fail_when_birthday_is_null() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@example.com");
        dto.setLogin("username");
        dto.setName("User Name");

        var violations = validator.validate(dto);

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("birthday")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("Дата рождения должна быть заполнена")));
    }

    @Test
    void should_fail_when_birthday_is_in_future() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@example.com");
        dto.setLogin("username");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.of(2030, 5, 15));

        var violations = validator.validate(dto);

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("birthday")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("дата рождения не может быть в будущем.")));
    }

    @Test
    void should_fail_when_birthday_is_today() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@example.com");
        dto.setLogin("username");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.now());

        var violations = validator.validate(dto);

        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("birthday")));
        assertTrue(violations.stream().anyMatch(v -> v.getMessageTemplate().equals("дата рождения не может быть в будущем.")));
    }

    @Test
    void should_pass_when_birthday_is_yesterday() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@example.com");
        dto.setLogin("username");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.now().minusDays(1));

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для даты рождения вчера");
    }

    @Test
    void should_pass_when_birthday_is_in_past() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("user@example.com");
        dto.setLogin("username");
        dto.setName("User Name");
        dto.setBirthday(LocalDate.of(1990, 5, 15));

        var violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Ожидается отсутствие исключений для даты рождения в прошлом");
    }

    @Test
    void should_report_all_violations() {
        NewUserDto dto = new NewUserDto();
        dto.setEmail("invalid");
        dto.setLogin("user name");
        dto.setBirthday(LocalDate.of(2030, 1, 1));

        var violations = validator.validate(dto);
        assertEquals(3, violations.size(), "Ожидается 3 исключения при некорректных данных");
    }
}
