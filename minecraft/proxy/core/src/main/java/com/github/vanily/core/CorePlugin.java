package com.github.vanily.core;

import com.github.vanily.core.executor.VelocityExecutor;
import com.github.vanily.core.listener.PlayerListener;
import com.github.vanily.core.listener.ProxyListener;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;
import org.slf4j.Logger;

@Plugin(id = "core", name = "core", version = "1.0", authors = {"Diogo"})
public class CorePlugin {

    private final ProxyServer server;
    private final VelocityExecutor executor;
    private final Logger logger;

    @Inject
    public CorePlugin(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
        this.executor = new VelocityExecutor(this, server);

        logger.info("Core initializated successfully.");
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getChannelRegistrar().register(MinecraftChannelIdentifier.from("velocity:main"));
        server.getEventManager().register(this, new PlayerListener(server, executor));
        server.getEventManager().register(this, new ProxyListener());
    }

}