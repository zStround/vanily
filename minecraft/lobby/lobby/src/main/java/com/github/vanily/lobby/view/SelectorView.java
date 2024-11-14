package com.github.vanily.lobby.view;

import me.saiintbrisson.minecraft.View;
import me.saiintbrisson.minecraft.ViewContext;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.github.vanily.core.color.ColorUtil.*;
import static com.github.vanily.lobby.constants.MessageConstants.*;
import static com.github.vanily.lobby.constants.ItemConstants.*;

public class SelectorView extends View {

    public SelectorView() {
        super(3, "Selecione um jogo");

        this.setCancelOnClick(true);
    }

    @Override
    protected void onRender(@NotNull ViewContext viewContext) {
        final Player player = viewContext.getPlayer();

        viewContext.slot(2, 2, MENU_SELECTOR_LOBBY.build());

        viewContext.slot(2, 4, MENU_SELECTOR_RANKUP.build());
        viewContext.slot(2, 5, MENU_SELECTOR_NEXUS.build());
        viewContext.slot(2, 6, MENU_SELECTOR_PVP.build());
        viewContext.slot(2, 7, MENU_SELECTOR_SKYWARS.build());
        viewContext.slot(2, 8, MENU_SELECTOR_BEDWARS.build());
    }
}
