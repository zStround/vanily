package com.github.vanily.core.executor;

import com.github.vanily.core.CorePlugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.scheduler.Scheduler;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;

public class VelocityExecutor implements Executor {

    private final CorePlugin plugin;
    private final ProxyServer server;
    private final Scheduler scheduler;

    public VelocityExecutor(CorePlugin plugin, ProxyServer server) {
        this.plugin = plugin;
        this.server = server;
        this.scheduler = server.getScheduler();
    }

    @Override
    public void execute(@NotNull Runnable command) {
        scheduler.buildTask(plugin, command).schedule();
    }

}
