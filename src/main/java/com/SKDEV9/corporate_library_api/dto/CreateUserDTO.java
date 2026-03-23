package com.SKDEV9.corporate_library_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDTO(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String senha,

        @NotBlank
        String matricula

) {
}
