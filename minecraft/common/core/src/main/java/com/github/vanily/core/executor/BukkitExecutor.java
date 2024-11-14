package com.github.vanily.core.executor;

import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

@AllArgsConstructor
public class BukkitExecutor implements Executor {
    private final JavaPlugin plugin;
    private final BukkitScheduler scheduler = Bukkit.getScheduler();

    @Override
    public void execute(@NotNull Runnable command) {
        scheduler.runTask(plugin, command);
    }
}
