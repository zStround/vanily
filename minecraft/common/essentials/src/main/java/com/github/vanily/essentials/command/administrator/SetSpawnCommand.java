package com.github.vanily.essentials.command.administrator;

import com.github.vanily.core.color.ColorUtil;
import com.github.vanily.core.command.CustomCommand;
import com.github.vanily.core.serializer.CustomLocation;
import com.github.vanily.essentials.EssentialsPlugin;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.github.vanily.essentials.constants.MessageConstants.*;

public class SetSpawnCommand extends CustomCommand {

    private final EssentialsPlugin plugin;

    public SetSpawnCommand(EssentialsPlugin plugin) {
        super("setspawn", "essentials.admin", true);

        this.plugin = plugin;
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {

        final Player player = (Player) commandSender;
        final Location location = player.getLocation();

        final CustomLocation customLocation = CustomLocation.of(location);

        plugin.getConfig().set("locations.spawn", customLocation.toString());
        plugin.saveConfig();

        commandSender.sendMessage(ColorUtil.colored(SET_SPAWN_SET));
    }
}
