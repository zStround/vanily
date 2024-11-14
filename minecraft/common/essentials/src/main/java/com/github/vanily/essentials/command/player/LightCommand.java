package com.github.vanily.essentials.command.player;

import com.github.vanily.core.color.ColorUtil;
import com.github.vanily.core.command.CustomCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static com.github.vanily.essentials.constants.MessageConstants.*;

public class LightCommand extends CustomCommand {

    public LightCommand() {
        super("light", null, true, "luz");
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {

        final Player player = (Player) commandSender;

        if (!player.hasPotionEffect(PotionEffectType.NIGHT_VISION)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 50));

            commandSender.sendMessage(ColorUtil.colored(LIGHT_ON));
            return;
        }

        player.removePotionEffect(PotionEffectType.NIGHT_VISION);

        commandSender.sendMessage(ColorUtil.colored(LIGHT_OFF));
    }
}
