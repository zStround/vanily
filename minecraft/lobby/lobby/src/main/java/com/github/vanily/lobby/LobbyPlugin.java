package com.github.vanily.lobby;

import com.github.vanily.core.command.CustomCommand;
import com.github.vanily.lobby.commands.BuildCommand;
import com.github.vanily.lobby.listeners.PlayerListener;
import com.github.vanily.lobby.view.LobbySelectorView;
import com.github.vanily.lobby.view.ProfileView;
import com.github.vanily.lobby.view.SelectorView;
import lombok.Getter;
import lombok.SneakyThrows;
import me.saiintbrisson.minecraft.ViewFrame;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

@Getter
public class LobbyPlugin extends JavaPlugin {
    private ViewFrame viewFrame;

    @Override
    public void onEnable() {
        System.out.println("Hello world!");

        this.saveDefaultConfig();
        this.registerCommands(
                new BuildCommand()
        );
        this.viewFrame = ViewFrame.of(this,
                new SelectorView(),
                new ProfileView(),
                new LobbySelectorView()
        ).register();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @SneakyThrows
    private void registerCommands(CustomCommand... customCommands) {
        final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        commandMapField.setAccessible(true);

        final CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());

        for (CustomCommand customCommand : customCommands)
            commandMap.register(customCommand.getName(), customCommand);
    }

    public static LobbyPlugin getInstance() {
        return getPlugin(LobbyPlugin.class);
    }
}
