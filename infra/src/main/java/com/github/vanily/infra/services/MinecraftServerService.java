package com.github.vanily.infra.services;

import com.github.dockerjava.api.exception.NotModifiedException;
import com.github.vanily.infra.domain.MinecraftContainer;
import com.github.vanily.infra.domain.MinecraftServer;
import com.github.vanily.infra.repositories.MinecraftServerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MinecraftServerService {

    private final DockerService dockerService;
    private final MinecraftServerRepository minecraftServerRepository;

    public MinecraftServer getAvailableServer(MinecraftServer.Type type) {

        final MinecraftServer minecraftServer = minecraftServerRepository.findAllByType(type)
                .stream()
                .findFirst()
                .orElseGet(() -> {

                    final String containerId = dockerService.createContainer(type.name().toLowerCase(), 25565, List.of());
                    dockerService.startContainer(containerId);

                    final MinecraftContainer container = dockerService.getContainer(containerId).orElseThrow();
                    final MinecraftServer server = new MinecraftServer(container, type);

                    minecraftServerRepository.save(server);
                    return server;
                });

        startServer(minecraftServer);
        return minecraftServer;
    }

    public void startServer(MinecraftServer server) {

        try {
            dockerService.startContainer(server.getContainer().getId());
        } catch (NotModifiedException ignored) {}

        waitForServerPort(server.getContainer().getHostname());
    }

    public List<MinecraftServer> getAllServers() {
        return minecraftServerRepository.findAll();
    }

    public List<MinecraftServer> getAllServers(MinecraftServer.Type type) {
        return minecraftServerRepository.findAllByType(type);
    }

    public void deleteAllServers() {

        minecraftServerRepository.findAll()
                .forEach(minecraftServer -> {
                    dockerService.stopContainer(minecraftServer.getContainer().getId());
                    dockerService.deleteContainer(minecraftServer.getContainer().getId());
                    minecraftServerRepository.deleteById(minecraftServer.getId());
                });

    }

    private void waitForServerPort(String hostname) {

        boolean isPortOpen = false;
        int attempts = 0;
        final int maxAttempts = 10;
        final int waitTime = 5000;

        while (!isPortOpen && attempts < maxAttempts) {
            isPortOpen = isPortListening(hostname);
            if (!isPortOpen) {
                attempts++;
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (!isPortOpen)
            throw new RuntimeException("There is a problem with minecraft server?");
    }

    private boolean isPortListening(String hostname) {

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(hostname, 25565), 2000);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}