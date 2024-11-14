package com.github.vanily.lobby.commands;

import com.github.vanily.core.command.CustomCommand;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static com.github.vanily.core.color.ColorUtil.*;
import static com.github.vanily.lobby.constants.MessageConstants.*;

@Getter
public class BuildCommand extends CustomCommand {
    public static List<String> builders = new ArrayList<>();

    public BuildCommand() {
        super("build", "vanily.admin", true, "construtor");
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {

        final Player player = (Player) commandSender;

        if (builders.contains(player.getName())) {
            builders.remove(player.getName());
            player.sendMessage(colored(BUILDER_OFF));
        } else {
            builders.add(player.getName());
            player.sendMessage(colored(BUILDER_ON));
        }

    }
}
