package com.github.vanily.authentication.login.command;

import com.github.vanily.core.color.ColorUtil;
import com.github.vanily.core.command.CustomCommand;
import com.github.vanily.core.executor.BukkitExecutor;
import com.github.vanily.core.messages.GeneralMessageConstants;
import com.github.vanily.core.request.ProxyRequest;
import com.github.vanily.core.request.Request;
import com.github.vanily.token.Token;
import com.github.vanily.token.TokenManager;
import com.google.gson.JsonObject;
import net.kyori.adventure.title.Title;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.net.http.HttpResponse;

import static com.github.vanily.authentication.login.constants.MessageConstants.*;

public class LoginCommand extends CustomCommand {

    private final BukkitExecutor executor;
    private final ProxyRequest proxyRequest;
    private final TokenManager tokenManager = Token.getTokenManager();

    public LoginCommand(BukkitExecutor executor, ProxyRequest proxyRequest) {
        super("login", null, true, "logar");
        this.executor = executor;
        this.proxyRequest = proxyRequest;
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {

        final Player player = (Player) commandSender;

        if (arguments.length != 1) {
            player.sendMessage(ColorUtil.colored(LOGIN_SYNTAX_ERROR));
            return;
        }

        if (tokenManager.isLoggedIn(player.getName().toLowerCase()).join()) {
            player.sendMessage(ColorUtil.colored(ALREADY_LOGGED));
            return;
        }

        JsonObject body = new JsonObject();
        body.addProperty("username", player.getName().toLowerCase());
        body.addProperty("password", arguments[0]);

        final Request request = new Request("http://users-service:8001/authentication/login", "POST", body);

        request.send()
                .thenAccept(response -> {

                    final HttpResponse<String> httpResponse = response.get();

                    if (httpResponse.statusCode() == 200) {

                        final JsonObject json = response.getBody();
                        final String accessToken = json.get("access_token").getAsString();
                        final String refreshToken = json.get("refresh_token").getAsString();
                        final long expiresIn = System.currentTimeMillis() + json.get("expires_in").getAsLong();
                        final long refreshExpiresIn = System.currentTimeMillis() + json.get("refresh_expires_in").getAsLong();

                        tokenManager.saveToken(player.getName().toLowerCase(), accessToken, refreshToken, expiresIn, refreshExpiresIn)
                                .whenCompleteAsync((result, throwable) -> {

                                    if (throwable != null || result.wasDiscarded()) {
                                        player.sendMessage(ColorUtil.colored(GeneralMessageConstants.INTERNAL_ERROR));
                                        return;
                                    }

                                    player.showTitle(
                                            Title
                                                    .title(
                                                            ColorUtil.colored(LOGGED_SUCCESSFULLY_TITLE),
                                                            ColorUtil.colored(LOGGED_SUCCESSFULLY_SUBTITLE)
                                                    )
                                    );

                                    player.sendMessage(ColorUtil.colored(LOGGED_SUCCESSFULLY));
                                    player.sendMessage(ColorUtil.colored(REDIRECT_TO_LOBBY));
                                    proxyRequest.availableServer(player, "lobby");

                                }, executor);

                        return;
                    }

                    player.sendMessage(ColorUtil.colored(LOGIN_WRONG_PASSWORD));
                });

    }

}
