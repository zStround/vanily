package com.github.vanily.usersservice.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDto(

        @NotBlank
        String username,

        @NotBlank
        String password

) {
}
