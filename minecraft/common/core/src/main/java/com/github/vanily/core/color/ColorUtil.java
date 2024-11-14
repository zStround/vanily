package com.github.vanily.core.color;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ColorUtil {

    public MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    public String GREEN_COLOR = "<#10ad3a>";
    public String RED_COLOR = "<#c23913>";
    public String BLUE_COLOR = "<#1ce8bf>";
    public String WHITE_COLOR = "<#ffffff>";

    public Component colored(String message) {

        if (message == null)
            return Component.empty();

        return MINI_MESSAGE.deserialize(message);
    }

    public Component[] colored(String... messages) {
        return Arrays.stream(messages).map(ColorUtil::colored).toArray(Component[]::new);
    }

    public List<Component> colored(List<String> description) {
        return description.stream().map(ColorUtil::colored).collect(Collectors.toList());
    }

}