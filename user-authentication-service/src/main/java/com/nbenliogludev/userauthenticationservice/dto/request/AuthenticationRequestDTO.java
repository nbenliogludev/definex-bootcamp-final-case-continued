package com.nbenliogludev.userauthenticationservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * @author nbenliogludev
 */
public record AuthenticationRequestDTO (

        @NotBlank(message = "Email is required.")
        @Email(message = "Please provide a valid email address.")
        @Length(max = 100, message = "Email can not be longer than 100 characters.")
        String email,

        @NotBlank(message = "Password is required.")
        String password

) {
}