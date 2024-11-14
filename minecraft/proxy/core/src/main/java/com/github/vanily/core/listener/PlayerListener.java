package com.github.vanily.core.listener;

import com.github.vanily.core.executor.VelocityExecutor;
import com.github.vanily.core.request.Request;
import com.google.gson.JsonObject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.velocitypowered.api.proxy.server.ServerInfo;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class PlayerListener {

    private final ProxyServer proxyServer;
    private final VelocityExecutor executor;

    @Subscribe
    void onPluginMessage(PluginMessageEvent event) {

        if (!event.getIdentifier().getId().equals("velocity:main"))
            return;

        final DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));

        try {

            final String subChannel = in.readUTF();

            if (subChannel.equals("available_server")) {

                final String username = in.readUTF();
                final String serverType = in.readUTF();

                final Request request = new Request("http://infra:8000/servers/available/" + serverType.toUpperCase(), "GET");

                request.send().thenAcceptAsync(body -> {

                    final JsonObject container = body.get("container").getAsJsonObject();
                    final String name = container.get("name").getAsString();
                    final String hostname = container.get("hostname").getAsString();
                    final Supplier<ServerInfo> serverInfo = () -> new ServerInfo(name, new InetSocketAddress(hostname, 25565));
                    final RegisteredServer registeredServer = proxyServer.getServer(name).orElse(proxyServer.registerServer(serverInfo.get()));

                    proxyServer.getPlayer(username).ifPresent(player -> player.createConnectionRequest(registeredServer).connect().join());

                }, executor);

            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
