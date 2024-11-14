package com.github.vanily.essentials;

import com.github.vanily.essentials.command.administrator.*;
import com.github.vanily.essentials.command.player.*;
import com.github.vanily.essentials.listener.PlayerListener;
import com.github.vanily.essentials.listener.WorldListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class EssentialsPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.register();
    }

    private void register() {

        new ClearChatCommand().register();
        new GamemodeCommand().register();
        new InvseeCommand().register();
        new FlyCommand().register();
        new ItemNameCommand().register();
        new SkullCommand().register();
        new AddLoreCommand().register();
        new ClearLoreCommand().register();
        new SetLoreCommand().register();

        new SetPlotsCommand(this).register();
        new SetSpawnCommand(this).register();

        new LightCommand().register();
        new TrashCommand().register();
        new CraftCommand().register();
        new PlotsCommand(this).register();
        new SpawnCommand(this).register();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
        Bukkit.getPluginManager().registerEvents(new WorldListener(this), this);
    }

}
