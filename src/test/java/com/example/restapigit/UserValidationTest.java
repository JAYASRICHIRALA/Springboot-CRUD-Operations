package com.example.restapigit;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Set;
import org.junit.jupiter.api.Test;
import com.example.restapigit.entity.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

class UserValidationTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void testInvalidEmail() {
        User user = new User();
        user.setName("Rajani");
        user.setEmail("invalid-email");
        user.setPhone("9347319203");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }

    @Test
    void testBlankName() {
        User user = new User();
        user.setName("");
        user.setEmail("rajanibangaru@gmail.com");
        user.setPhone("9347319203");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    void testInvalidPhone() {
        User user = new User();
        user.setName("Rajani");
        user.setEmail("rajanibangaru@gmail.com");
        user.setPhone("12345");

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("phone")));
    }
}
