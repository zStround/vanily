package com.github.vanily.infra.dto;

import com.github.vanily.infra.domain.MinecraftServer;

public record MinecraftServerDto(

        MinecraftContainerDto container,
        MinecraftServer.Type type,
        boolean maintenance

) {}
