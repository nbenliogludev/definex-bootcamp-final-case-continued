package com.nbenliogludev.userauthenticationservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * @author nbenliogludev
 */
public record UserCreateRequestDTO (

        @NotBlank(message = "Name is required.")
        @Length(min = 2, max = 100, message = "Name must be between 2 and 100 characters.")
        String name,

        @NotBlank(message = "Surname is required.")
        @Length(max = 80, message = "Surname can not be longer than 80 characters.")
        String surname,

        @NotBlank(message = "Email is required.")
        @Email(message = "Please provide a valid email address.")
        @Length(max = 100, message = "Email can not be longer than 100 characters.")
        String email,

        @NotBlank(message = "Password is required.")
        String password

) {
}
