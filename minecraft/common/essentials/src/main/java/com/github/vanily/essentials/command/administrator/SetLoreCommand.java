package com.github.vanily.essentials.command.administrator;

import com.github.vanily.core.color.ColorUtil;
import com.github.vanily.core.command.CustomCommand;
import com.github.vanily.core.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

import static com.github.vanily.essentials.constants.MessageConstants.*;

public class SetLoreCommand extends CustomCommand {

    public SetLoreCommand() {
        super("setlore", "essentials.admin", true, "slore");
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {
        if (arguments.length < 2) {
            commandSender.sendMessage("§cUtilize, /slore <linha> <texto>");
            return;
        }

        final Player player = (Player) commandSender;
        final ItemStack item = player.getItemInHand();

        if (item.getType() == Material.AIR) {
            commandSender.sendMessage(ColorUtil.colored(NEED_ITEM));
            return;
        }

        final int line = Integer.parseInt(arguments[0]);
        final String text = String.join(" ", Arrays.copyOfRange(arguments, 1, arguments.length)).replace("&", "§");

        final ItemBuilder itemBuilder = new ItemBuilder(item);

        if (line < 0 || line >= item.getLore().size()) {
            commandSender.sendMessage(INVALID_LINE);
            return;
        }

        List<String> lore = itemBuilder.getLore();
        lore.set(line, text);

        itemBuilder.lore(lore);

        player.getInventory().setItemInHand(itemBuilder.build());

        commandSender.sendMessage(ColorUtil.colored(SET_LORE));
    }
}