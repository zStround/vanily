package com.github.vanily.core;

import com.github.vanily.core.executor.BukkitExecutor;
import com.github.vanily.core.request.ProxyRequest;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class CorePlugin extends JavaPlugin {

    private final BukkitExecutor bukkitExecutor = new BukkitExecutor(this);

    public static CorePlugin getInstance() {
        return getPlugin(CorePlugin.class);
    }

}
