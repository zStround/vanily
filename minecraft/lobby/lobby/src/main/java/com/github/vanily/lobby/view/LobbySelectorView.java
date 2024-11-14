package com.github.vanily.lobby.view;

import me.saiintbrisson.minecraft.View;
import me.saiintbrisson.minecraft.ViewContext;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LobbySelectorView extends View {

    public LobbySelectorView() {
        super(3, "Selecione um lobby");

        this.setCancelOnClick(true);
    }

    @Override
    protected void onRender(@NotNull ViewContext viewContext) {
        final Player player = viewContext.getPlayer();


    }
}
