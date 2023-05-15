package com.pragma.powerup.usermicroservice;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRequestDtoTest {

    private Validator validator;
    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @ParameterizedTest(name = "function must return one violation of Jakarta validation")
    @ValueSource(strings = {"", " ", "\t", "\n"})
    @DisplayName("Test name cannot be blank")
    void testNameCannotBeBlank(String stringEmpty) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName(stringEmpty);
        userRequestDto.setSurname("surname");
        userRequestDto.setDniNumber("123456789");
        userRequestDto.setPhone("+1234567890");
        userRequestDto.setDateBirthday(LocalDate.of(2000, 1, 1));
        userRequestDto.setMail("test@example.com");
        userRequestDto.setPassword("password");

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("name null not allowed")));
    }

    @ParameterizedTest(name = "function must return one violation of Jakarta validation")
    @ValueSource(strings = {"", " ", "\t", "\n"})
    @DisplayName("Test surname cannot be blank")
    void testSurNameCannotBeBlank(String stringEmpty) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("name");
        userRequestDto.setSurname(stringEmpty);
        userRequestDto.setDniNumber("123456789");
        userRequestDto.setPhone("+1234567890");
        userRequestDto.setDateBirthday(LocalDate.of(2000, 1, 1));
        userRequestDto.setMail("test@example.com");
        userRequestDto.setPassword("password");

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("surname null not allowed")));
    }

    @ParameterizedTest(name = "function must return one Jakarta validation violations")
    @ValueSource(strings = {"1234567", "12345678911234"})
    @DisplayName("Test dni number out of range")
    void testDniNumberOutOfRange(String someDniNumberInvalid) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("name");
        userRequestDto.setSurname("surname");
        userRequestDto.setDniNumber(someDniNumberInvalid);
        userRequestDto.setPhone("+1234567890");
        userRequestDto.setDateBirthday(LocalDate.of(2000, 1, 1));
        userRequestDto.setMail("test@example.com");
        userRequestDto.setPassword("password");

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("size dni [8 - 13]")));
    }

    @ParameterizedTest(name = "function must return one Jakarta validation violations")
    @ValueSource(strings = {"123ABC4567", "123456911ABC", "A1234567", " 546656516", "123A56789", "[1-9]123564"})
    @DisplayName("Test dni number alphanumeric")
    void testDniNumberAlphanumeric(String someDniNumberInvalid) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("name");
        userRequestDto.setSurname("surname");
        userRequestDto.setDniNumber(someDniNumberInvalid);
        userRequestDto.setPhone("+1234567890");
        userRequestDto.setDateBirthday(LocalDate.of(2000, 1, 1));
        userRequestDto.setMail("test@example.com");
        userRequestDto.setPassword("password");

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("numeric field")));
    }

    @ParameterizedTest(name = "function must return one Jakarta validation violations")
    @ValueSource(strings = {"123456789", "12345678911234"})
    @DisplayName("Test phone out of range")
    void testPhoneOutOfRange(String somePhoneInvalid) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("name");
        userRequestDto.setSurname("surname");
        userRequestDto.setDniNumber("123456789");
        userRequestDto.setPhone(somePhoneInvalid);
        userRequestDto.setDateBirthday(LocalDate.of(2000, 1, 1));
        userRequestDto.setMail("test@example.com");
        userRequestDto.setPassword("password");

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("size phone [10 - 13]")));
    }

    @ParameterizedTest(name = "function must return one Jakarta validation violations")
    @ValueSource(strings = {"1234567891A", "123+456789", " 123456789+", "123A567890"})
    @DisplayName("Test phone regex")
    void testPhoneregexc(String somePhonerInvalid) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("name");
        userRequestDto.setSurname("surname");
        userRequestDto.setDniNumber("123456789");
        userRequestDto.setPhone(somePhonerInvalid);
        userRequestDto.setDateBirthday(LocalDate.of(2000, 1, 1));
        userRequestDto.setMail("test@example.com");
        userRequestDto.setPassword("password");

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("numeric field, (optional) start with the area code of your country")));
    }

    @ParameterizedTest(name = "function must return one violation of Jakarta validation")
    @ValueSource(strings = {""})
    @DisplayName("Test date birthday cannot be null")
    void testDateBirthDayCannotBeBlank(String dateNotValid) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("name");
        userRequestDto.setSurname("surname");
        userRequestDto.setDniNumber("123456789");
        userRequestDto.setPhone("+1234567890");
        userRequestDto.setDateBirthday(null);
        userRequestDto.setMail("test@example.com");
        userRequestDto.setPassword("password");

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("date birthday null or underage")));
    }

    @ParameterizedTest(name = "function must return one violation of Jakarta validation")
    @ValueSource(strings = {"2006-03-04", "2006-01-01", "2023-05-02", "2005-12-15"})
    @DisplayName("Test date birthday with underage")
    void testDateBirthDayUnderAge(String dateWithUnderage) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("name");
        userRequestDto.setSurname("surname");
        userRequestDto.setDniNumber("123456789");
        userRequestDto.setPhone("+1234567890");
        userRequestDto.setDateBirthday(LocalDate.parse(dateWithUnderage));
        userRequestDto.setMail("test@example.com");
        userRequestDto.setPassword("password");

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("date birthday null or underage")));
    }

    @ParameterizedTest(name = "function must return one violation of Jakarta validation")
    @ValueSource(strings = {"", "null"})
    @DisplayName("Test mail cannot be empty or null")
    void testMailCannotBeBlank(String stringEmpty) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("name");
        userRequestDto.setSurname("surname");
        userRequestDto.setDniNumber("123456789");
        userRequestDto.setPhone("+1234567890");
        userRequestDto.setDateBirthday(LocalDate.of(2000, 1, 1));
        userRequestDto.setMail(stringEmpty);
        if(stringEmpty.equals("null")) userRequestDto.setMail(null);
        userRequestDto.setPassword("password");
        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("mail null not allowed")));
    }

    @ParameterizedTest(name = "function must return one violation of Jakarta validation")
    @ValueSource(strings = {"usernamemail.com", "@mail.com", "%$%!%&/)()@mail.com"})
    @DisplayName("Test mail invalid")
    void testMailInvalid(String someMailInvalid) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("name");
        userRequestDto.setSurname("surname");
        userRequestDto.setDniNumber("123456789");
        userRequestDto.setPhone("+1234567890");
        userRequestDto.setDateBirthday(LocalDate.of(2000, 1, 1));
        userRequestDto.setMail(someMailInvalid);
        userRequestDto.setPassword("password");

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("mail not valid")));
    }

    @ParameterizedTest(name = "function must return one violation of Jakarta validation")
    @ValueSource(strings = {"", " ", "\t", "\n"})
    @DisplayName("Test password cannot be blank")
    void testPasswordCannotBeBlank(String stringEmpty) {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("name");
        userRequestDto.setSurname("surname");
        userRequestDto.setDniNumber("123456789");
        userRequestDto.setPhone("+1234567890");
        userRequestDto.setDateBirthday(LocalDate.of(2000, 1, 1));
        userRequestDto.setMail("username@mail.com");
        userRequestDto.setPassword(stringEmpty);

        Set<ConstraintViolation<UserRequestDto>> violations = validator.validate(userRequestDto);
        assertEquals(1, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("password null not allowed")));
    }

}
