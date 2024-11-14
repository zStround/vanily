package com.github.vanily.core.listener;

import com.github.vanily.core.request.Request;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;

public class ProxyListener {

    @Subscribe
    void onProxyShutdown(ProxyShutdownEvent event) {

        new Request("http://infra:8000/servers", "DELETE").send().join();

    }

}
