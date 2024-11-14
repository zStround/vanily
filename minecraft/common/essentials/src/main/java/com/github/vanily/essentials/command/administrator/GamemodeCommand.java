package com.github.vanily.essentials.command.administrator;

import com.github.vanily.core.command.CustomCommand;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.github.vanily.core.color.ColorUtil.*;
import static com.github.vanily.essentials.constants.MessageConstants.*;

public class GamemodeCommand extends CustomCommand {

    public GamemodeCommand() {
        super("gamemode", "vanily.admin", false, "gm");
    }

    @Override
    protected void onCommand(CommandSender commandSender, String[] arguments) {
        if (!(commandSender instanceof Player)) {
            if (arguments.length != 2) {
                commandSender.sendMessage(colored(GAMEMODE_COMMAND_SYNTAX_ERROR));
                return;
            }

            Player player = Bukkit.getPlayerExact(arguments[1]);
            if (player == null) {
                commandSender.sendMessage(colored(PLAYER_NOT_FOUND));
                return;
            }

            GameMode mode = getGameMode(arguments[0]);
            if (mode == null) {
                commandSender.sendMessage(colored(GAMEMODE_NON_EXISTENT));
                return;
            }

            player.setGameMode(mode);
            commandSender.sendMessage(colored(GAMEMODE_PLAYER_CHANGED.replace("{player}", player.getName()).replace("{mode}", mode.name().toUpperCase())));
            return;
        }

        if (arguments.length > 2 || arguments.length < 1) {
            commandSender.sendMessage(colored(GAMEMODE_COMMAND_SYNTAX_ERROR));
            return;
        }

        if (arguments.length == 2) {
            Player player = Bukkit.getPlayerExact(arguments[1]);
            if (player == null) {
                commandSender.sendMessage(colored(PLAYER_NOT_FOUND));
                return;
            }

            GameMode mode = getGameMode(arguments[0]);
            if (mode == null) {
                commandSender.sendMessage(colored(GAMEMODE_NON_EXISTENT));
                return;
            }

            player.setGameMode(mode);
            commandSender.sendMessage(colored(GAMEMODE_PLAYER_CHANGED.replace("{player}", player.getName()).replace("{mode}", mode.name().toUpperCase())));
            return;
        }

        GameMode mode = getGameMode(arguments[0]);
        if (mode == null) {
            commandSender.sendMessage(colored(GAMEMODE_NON_EXISTENT));
            return;
        }

        ((Player) commandSender).setGameMode(mode);
        commandSender.sendMessage(colored(GAMEMODE_CHANGED.replace("{mode}", mode.name().toUpperCase())));
    }

    private GameMode getGameMode(String gamemode) {
        GameMode gameMode = null;

        try {
            int gmValue = Integer.parseInt(gamemode);
            gameMode = GameMode.getByValue(gmValue);
        } catch (NumberFormatException ignored) {
            try {
                gameMode = GameMode.valueOf(gamemode.toUpperCase());
            } catch (IllegalArgumentException ignored2) {
                return null;
            }
        }

        return gameMode;
    }

}
