package com.github.vanily.essentials.command.player;

import com.github.vanily.core.color.ColorUtil;
import com.github.vanily.core.command.CustomCommand;
import com.github.vanily.core.serializer.CustomLocation;
import com.github.vanily.essentials.EssentialsPlugin;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.github.vanily.essentials.constants.MessageConstants.*;

public class SpawnCommand extends CustomCommand {

    private final EssentialsPlugin plugin;

    public SpawnCommand(EssentialsPlugin plugin) {
        super("spawn", null, true);

        this.plugin = plugin;
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {
        final Player player = (Player) commandSender;

        final CustomLocation customLocation = CustomLocation.of(plugin.getConfig().getString("locations.spawn"));
        final Location location = customLocation.getLocation();

        player.teleport(location);
        player.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);

        commandSender.sendMessage(ColorUtil.colored(SPAWN_TELEPORT));
    }
}
