package com.example.example.validation.validator;

import com.example.example.services.UserService;
import com.example.example.validation.annotation.EmailUniqueValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailUniqueValueValidator implements ConstraintValidator<EmailUniqueValue, String> {

    private final UserService userService;

    @Autowired
    public EmailUniqueValueValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return true;
        }

        return this.userService.checkForNonExistingEmail(value);
    }

}