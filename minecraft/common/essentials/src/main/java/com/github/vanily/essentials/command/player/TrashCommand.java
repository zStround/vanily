package com.github.vanily.essentials.command.player;

import com.github.vanily.core.command.CustomCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TrashCommand extends CustomCommand {

    public TrashCommand() {
        super("trash", null, true, "lixo");
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {

        final Player player = (Player) commandSender;

        final Inventory inventory = Bukkit.createInventory(player, 36, "Lixeira");
        player.openInventory(inventory);

    }
}
