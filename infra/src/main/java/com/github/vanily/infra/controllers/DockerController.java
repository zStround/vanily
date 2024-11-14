package com.github.vanily.infra.controllers;

import com.github.vanily.infra.dto.CreateContainerDto;
import com.github.vanily.infra.services.DockerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/containers")
@RequiredArgsConstructor
public class DockerController {

    private final DockerService dockerService;

    @PostMapping("/create")
    public ResponseEntity<Object> createContainer(@RequestBody @Valid CreateContainerDto dto) {
        return ResponseEntity
                .ok(dockerService.createContainer(dto.image(), dto.port(), dto.env()));
    }

    @PostMapping("/start/{id}")
    public ResponseEntity<Object> startContainer(@PathVariable("id") String id) {
        dockerService.startContainer(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteContainer(@PathVariable("id") String id) {
        dockerService.deleteContainer(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/stop/{id}")
    public ResponseEntity<Object> stopContainer(@PathVariable("id") String id) {
        dockerService.stopContainer(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/restart/{id}")
    public ResponseEntity<Object> restartContainer(@PathVariable("id") String id) {
        dockerService.restartContainer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Object> getContainers() {
        return ResponseEntity
                .ok(dockerService.getContainers(List.of()));
    }

    @GetMapping("/{network}")
    public ResponseEntity<Object> getContainers(@PathVariable("network") String network) {
        return ResponseEntity
                .ok(dockerService.getContainers(List.of(network)));
    }

}
