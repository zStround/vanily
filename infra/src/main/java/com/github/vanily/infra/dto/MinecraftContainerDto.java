package com.github.vanily.infra.dto;

import com.github.vanily.infra.domain.MinecraftContainer;
import lombok.Builder;

@Builder
public record MinecraftContainerDto(

        String id,

        String name,

        String hostname

) {

    public MinecraftContainerDto(MinecraftContainer container) {
        this(container.getId(), container.getName(), container.getHostname());
    }

}
