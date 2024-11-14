package com.github.vanily.core.request;

import com.github.vanily.core.CorePlugin;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@RequiredArgsConstructor
public class ProxyRequest {

    private final static String AVAILABLE_SERVER_MESSAGE = "available_server";
    private final JavaPlugin plugin;

    public void availableServer(Player player, String serverType) {

        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byteArray);

        try {
            out.writeUTF(AVAILABLE_SERVER_MESSAGE);
            out.writeUTF(player.getName());
            out.writeUTF(serverType);
        } catch (IOException ignored) {}

        System.out.println("teste");
        player.sendPluginMessage(plugin, "velocity:main", byteArray.toByteArray());
    }

    public static ProxyRequest create(JavaPlugin plugin) {
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "velocity:main");
        return new ProxyRequest(plugin);
    }

}
