package com.github.vanily.infra.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateContainerDto(

        @NotEmpty
        String image,

        @NotNull
        int port,

        @NotNull
        List<String> env

) {
}
