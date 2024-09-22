package com.example.example.Dtos;

import com.example.example.validation.annotation.EmailUniqueValue;
import com.example.example.validation.annotation.PasswordMatching;
import com.example.example.validation.annotation.UsernameUniqueValue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@PasswordMatching(
        password = "password",
        confirmPassword = "confirmPassword",
        message = "Password and Confirm Password must be matched!"
)
@Data
public class UserDTO {

    @NotNull
    @UsernameUniqueValue
    @Size(min = 2, message = "Username must be at least 2 characters!")
    private String username;

    @NotNull
    @Size(min = 2, message = "Full name must be at least 2 characters!")
    private String fullName;

    @NotNull
    @EmailUniqueValue
    @Email(message = "Please, enter valid email address!")
    private String email;

    @Positive(message = "Age of user must be positive value!")
    private Integer age;

    @NotNull
    @Size(min = 2, message = "Password must be at least 2 characters!")
    private String password;

    @NotNull
    @Size(min = 2)
    private String confirmPassword;
}