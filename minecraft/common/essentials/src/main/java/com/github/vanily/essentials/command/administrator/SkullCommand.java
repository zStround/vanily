package com.github.vanily.essentials.command.administrator;

import com.github.vanily.core.color.ColorUtil;
import com.github.vanily.core.command.CustomCommand;
import com.github.vanily.core.item.CustomSkull;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static com.github.vanily.essentials.constants.MessageConstants.*;

public class SkullCommand extends CustomCommand {

    public SkullCommand() {
        super("skull", "essentials.admin", true, "head");
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {
        if (arguments.length != 1) {
            commandSender.sendMessage("§cUtilize, /skull <url>");
            return;
        }

        final Player player = (Player) commandSender;

        final String url = arguments[0];

        final ItemStack item = CustomSkull.skullFromUrl(url);

        player.getInventory().addItem(item);

        commandSender.sendMessage(ColorUtil.colored(SKULL));
    }
}