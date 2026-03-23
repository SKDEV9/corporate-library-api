package com.SKDEV9.corporate_library_api.dto;


import jakarta.validation.constraints.NotBlank;

public record CreateBookDTO(
        @NotBlank
        String title,

        @NotBlank
        String author,

        @NotBlank
        String isbn
) {
}
