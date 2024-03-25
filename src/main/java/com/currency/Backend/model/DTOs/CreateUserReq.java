package com.currency.Backend.model.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.UniqueElements;


import java.util.Set;

@Builder
public record CreateUserReq(
        @NotBlank(message = "name is required")
        String name,
        @NotBlank(message = "email is required")
        @Email(message = "email should be right format")
        String email,
        @NotBlank(message = "password is required")
        String password,
        CreateAccountReq createAccountReq
) {
}
