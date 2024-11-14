package com.github.vanily.authentication.login.command;

import com.github.vanily.core.color.ColorUtil;
import com.github.vanily.core.command.CustomCommand;
import com.github.vanily.core.executor.BukkitExecutor;
import com.github.vanily.core.messages.GeneralMessageConstants;
import com.github.vanily.core.request.Request;
import com.google.gson.JsonObject;
import net.kyori.adventure.title.Title;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.net.http.HttpResponse;

import static com.github.vanily.authentication.login.constants.MessageConstants.*;

public class RegisterCommand extends CustomCommand {

    private final BukkitExecutor executor;

    public RegisterCommand(BukkitExecutor executor) {
        super("register", null, true, "registrar");
        this.executor = executor;
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {

        final Player player = (Player) commandSender;

        if (arguments.length != 2) {
            player.sendMessage(ColorUtil.colored(REGISTER_SYNTAX_ERROR));
            return;
        }

        if (!arguments[0].equals(arguments[1])) {
            player.sendMessage(ColorUtil.colored(PASSWORD_ARE_NOT_EQUALS));
            return;
        }

        JsonObject body = new JsonObject();
        body.addProperty("username", player.getName().toLowerCase());
        body.addProperty("password", arguments[0]);

        final Request request = new Request("http://users-service:8001/authentication/register", "POST", body);

        request.send()
                .thenAcceptAsync(response -> {

                    final HttpResponse<String> httpResponse = response.get();

                    if (httpResponse.statusCode() == 201) {

                        player.showTitle(
                                Title
                                        .title(
                                                ColorUtil.colored(REGISTERED_SUCCESSFULLY_TITLE),
                                                ColorUtil.colored(REGISTERED_SUCCESSFULLY_SUBTITLE)
                                        )
                        );

                        player.sendMessage(ColorUtil.colored(REGISTERED_SUCCESSFULLY));
                        return;
                    }

                    if (httpResponse.statusCode() == 409) {
                        player.sendMessage(ColorUtil.colored(ALREADY_REGISTERED));
                        return;
                    }

                    player.sendMessage(ColorUtil.colored(GeneralMessageConstants.INTERNAL_ERROR));
                }, executor);

    }

}
