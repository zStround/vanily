package com.github.vanily.essentials.command.administrator;

import com.github.vanily.core.color.ColorUtil;
import com.github.vanily.core.command.CustomCommand;
import com.github.vanily.core.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static com.github.vanily.essentials.constants.MessageConstants.*;


public class AddLoreCommand extends CustomCommand {

    public AddLoreCommand() {
        super("addlore", "essentials.admin", true, "lore");
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {
        if (arguments.length < 1) {
            commandSender.sendMessage("§cUtilize, /lore <nome>");
            return;
        }

        final Player player = (Player) commandSender;
        final ItemStack item = player.getItemInHand();

        if (item.getType() == Material.AIR) {
            commandSender.sendMessage(ColorUtil.colored(NEED_ITEM));
            return;
        }

        final String lore = String.join(" ", arguments).replace("&", "§");

        final ItemBuilder itemBuilder = new ItemBuilder(item)
                .addLore(lore);

        player.getInventory().setItemInHand(itemBuilder.build());

        commandSender.sendMessage(ColorUtil.colored(LORE_ITEM));
    }
}