package com.github.vanily.authentication.login.listener;

import com.github.vanily.core.color.ColorUtil;
import com.github.vanily.core.executor.BukkitExecutor;
import com.github.vanily.core.request.Request;
import com.github.vanily.token.Token;
import com.github.vanily.token.TokenManager;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.title.Title;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.net.http.HttpResponse;

import static com.github.vanily.authentication.login.constants.MessageConstants.*;

@RequiredArgsConstructor
public class PlayerListener implements Listener {

    private final TokenManager tokenManager = Token.getTokenManager();
    private final BukkitExecutor executor;

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent event) {

        final Player player = event.getPlayer();
        tokenManager.removeToken(player.getName().toLowerCase());

        final Request request = new Request("http://users-service:8001/authentication/" + player.getName().toLowerCase(), "GET");

        request.send()
                .thenAcceptAsync(response -> {

                    final HttpResponse<String> httpResponse = response.get();
                    final String subTitle = httpResponse.statusCode() == 200 ? PLAYER_LOGIN_SUBTITLE : PLAYER_REGISTER_SUBTITLE;

                    player.showTitle(
                            Title
                                    .title(
                                            ColorUtil.colored(PLAYER_JOIN_TITLE),
                                            ColorUtil.colored(subTitle)
                                    )
                    );

                }, executor);

        player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
    }

}
