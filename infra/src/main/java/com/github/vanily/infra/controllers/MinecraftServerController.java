package com.github.vanily.infra.controllers;

import com.github.vanily.infra.domain.MinecraftContainer;
import com.github.vanily.infra.domain.MinecraftServer;
import com.github.vanily.infra.dto.MinecraftContainerDto;
import com.github.vanily.infra.dto.MinecraftServerDto;
import com.github.vanily.infra.services.MinecraftServerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/servers")
public class MinecraftServerController {

    private final MinecraftServerService minecraftServerService;

    @GetMapping("/available/{type}")
    public ResponseEntity<MinecraftServerDto> getAvailableServer(@PathVariable MinecraftServer.Type type) {

        final MinecraftServer minecraftServer = minecraftServerService.getAvailableServer(type);
        final MinecraftContainer minecraftContainer = minecraftServer.getContainer();
        final MinecraftServerDto dto = new MinecraftServerDto(new MinecraftContainerDto(minecraftContainer), type, minecraftServer.isMaintenance());

        return ResponseEntity
                .ok(dto);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAllServers() {
        minecraftServerService.deleteAllServers();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<MinecraftServer>> getAllServers() {
        return ResponseEntity.ok(minecraftServerService.getAllServers());
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<MinecraftServer>> getServer(@PathVariable MinecraftServer.Type type) {
        return ResponseEntity.ok(minecraftServerService.getAllServers(type));
    }

}
