package com.github.vanily.essentials.command.player;

import com.github.vanily.core.command.CustomCommand;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CraftCommand extends CustomCommand {

    public CraftCommand() {
        super("craft", null, true);
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {
        final Player player = (Player) commandSender;

        player.openWorkbench(player.getLocation(), true);
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
    }
}
