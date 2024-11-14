package com.github.vanily.core.command;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;

@Getter
public abstract class CustomCommand extends Command {
    private final Set<CustomCommand> subCommands = Sets.newHashSet();
    private final String permission;
    private final boolean playerOnly;

    public CustomCommand(String name, String permission, Boolean playerOnly, String... aliases) {
        super(name);

        this.permission = permission;
        this.playerOnly = playerOnly;

        if (aliases.length > 0)
            setAliases(Lists.newArrayList(aliases));

        if (permission != null)
            setPermission(permission);

        setUsage("§cUtilize o seguinte comando /" + name);
    }

    protected abstract void onCommand(CommandSender commandSender, String[] arguments);

    public void executeRaw(CommandSender commandSender, String[] strings) {

        if (!(commandSender instanceof Player) && playerOnly) {
            commandSender.sendMessage("§cApenas jogadores podem usar este comando.");
            return;
        }

        if (permission != null && !testPermissionSilent(commandSender)) {
            commandSender.sendMessage("§cVocê não pode usar este comando.");
            return;
        }

        if (strings.length > 0) {
            CustomCommand subCommandFound = null;

            for (CustomCommand subCommand : subCommands) {
                if (subCommand.getName().equalsIgnoreCase(strings[0])
                        || subCommand.getAliases().stream().anyMatch(alias -> alias.equalsIgnoreCase(strings[0]))) {
                    subCommandFound = subCommand;
                    break;
                }
            }

            if (subCommandFound != null) {
                subCommandFound.executeRaw(commandSender, Arrays.copyOfRange(strings, 1, strings.length));
                return;
            }
        }

        this.onCommand(commandSender, strings);
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, String[] strings) {
        executeRaw(commandSender, strings);
        return true;
    }

    public void registerSubCommands(CustomCommand... customCommands) {
        subCommands.addAll(Arrays.asList(customCommands));
    }

    @SneakyThrows
    public void register() {
        final Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        commandMapField.setAccessible(true);

        final CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());

        commandMap.register(this.getName(), this);
    }

}