package com.github.vanily.essentials.command.administrator;

import com.github.vanily.core.command.CustomCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.github.vanily.core.color.ColorUtil.*;
import static com.github.vanily.essentials.constants.MessageConstants.*;

public class InvseeCommand extends CustomCommand {

    public InvseeCommand() {
        super("invsee", "vanily.admin", true, "inv");
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {
        if (arguments.length != 1) {
            commandSender.sendMessage(colored(INVSEE_COMMAND_SYNTAX_ERROR));
            return;
        }

        final Player player = (Player) commandSender;
        final Player target = Bukkit.getPlayer(arguments[0]);

        if (target == null) {
            commandSender.sendMessage(colored(PLAYER_NOT_FOUND));
            return;
        }

        if (target.equals(commandSender)) {
            commandSender.sendMessage(colored(CANNOT_OPEN_OWN_INVENTORY));
            return;
        }

        player.openInventory(target.getInventory());

        commandSender.sendMessage(colored(OPENED_TARGET_INVENTORY.replace("{player}", target.getName())));
    }

}
