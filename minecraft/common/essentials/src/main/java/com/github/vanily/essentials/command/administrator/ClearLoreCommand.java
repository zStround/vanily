package com.github.vanily.essentials.command.administrator;

import com.github.vanily.core.color.ColorUtil;
import com.github.vanily.core.command.CustomCommand;
import com.github.vanily.core.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static com.github.vanily.essentials.constants.MessageConstants.*;

public class ClearLoreCommand extends CustomCommand {

    public ClearLoreCommand() {
        super("clearlore", "essentials.admin", true, "clore");
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {

        final Player player = (Player) commandSender;
        final ItemStack item = player.getItemInHand();

        if (item.getType() == Material.AIR) {
            commandSender.sendMessage(ColorUtil.colored(NEED_ITEM));
            return;
        }

        final ItemBuilder itemBuilder = new ItemBuilder(item)
                .clearLore();

        player.getInventory().setItemInHand(itemBuilder.build());

        commandSender.sendMessage(ColorUtil.colored(LORE_CLEAR));
    }
}