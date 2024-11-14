package com.github.vanily.essentials.command.administrator;

import com.github.vanily.core.color.ColorUtil;
import com.github.vanily.core.command.CustomCommand;
import com.github.vanily.essentials.constants.MessageConstants;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand extends CustomCommand {

    public FlyCommand() {
        super("fly","essentials.mod",true, "voar");
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {
        Player player = (Player) commandSender;

        if (arguments.length == 1) {

            final Player target = Bukkit.getPlayerExact(arguments[0]);

            if (target == null) {
                commandSender.sendMessage(ColorUtil.colored(MessageConstants.PLAYER_NOT_FOUND));
                return;
            }

            player = target;
        }

        if (player.getAllowFlight()) {
            player.setFlying(false);
            player.setAllowFlight(false);
        } else {
            player.setAllowFlight(true);
        }

        final String state = (player.getAllowFlight() ? "ativou" : "desativou");

        commandSender.sendMessage(ColorUtil.colored(MessageConstants.FLY.replace("{state}", state).replace("{player}", player.getName())));
    }
}
