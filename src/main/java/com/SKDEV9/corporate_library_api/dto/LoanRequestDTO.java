package com.SKDEV9.corporate_library_api.dto;

import jakarta.validation.constraints.NotNull;

public record LoanRequestDTO(

        @NotNull
        Long userId,

        @NotNull
        Long bookId

) {
}
