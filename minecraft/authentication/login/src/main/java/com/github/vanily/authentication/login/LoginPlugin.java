package com.github.vanily.authentication.login;

import com.github.vanily.authentication.login.command.LoginCommand;
import com.github.vanily.authentication.login.command.RegisterCommand;
import com.github.vanily.authentication.login.listener.PlayerListener;
import com.github.vanily.core.executor.BukkitExecutor;
import com.github.vanily.core.request.ProxyRequest;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LoginPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        final BukkitExecutor bukkitExecutor = new BukkitExecutor(this);
        final ProxyRequest proxyRequest = ProxyRequest.create(this);

        new RegisterCommand(bukkitExecutor).register();
        new LoginCommand(bukkitExecutor, proxyRequest).register();
        Bukkit.getPluginManager().registerEvents(new PlayerListener(bukkitExecutor), this);

    }

}
