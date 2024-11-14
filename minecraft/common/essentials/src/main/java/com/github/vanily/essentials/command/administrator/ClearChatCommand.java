package com.github.vanily.essentials.command.administrator;

import com.github.vanily.core.command.CustomCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import static com.github.vanily.core.color.ColorUtil.*;
import static com.github.vanily.essentials.constants.MessageConstants.*;

public class ClearChatCommand extends CustomCommand {

    public ClearChatCommand() {
        super("clearchat", "vanily.admin", false, "cc", "limparchat");
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {
        for (int i = 0; i < 100; i++)
            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(""));

        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(colored(CLEARCHAT_NOTIFY.replace("{player}", commandSender.getName()))));
    }

}
