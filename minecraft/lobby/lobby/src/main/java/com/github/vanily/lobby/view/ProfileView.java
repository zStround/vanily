package com.github.vanily.lobby.view;

import com.github.vanily.core.item.CustomSkull;
import com.github.vanily.core.item.ItemBuilder;
import me.saiintbrisson.minecraft.View;
import me.saiintbrisson.minecraft.ViewContext;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ProfileView extends View {

    public ProfileView() {
        super(5, "Meu perfil");

        this.setCancelOnClick(true);
    }

    @Override
    protected void onRender(@NotNull ViewContext viewContext) {
        final Player player = viewContext.getPlayer();

        viewContext.slot(2, 5, getHeadProfile(player));

        viewContext.slot(4, 4, getStats());
        viewContext.slot(4, 5, getPreferences());
        viewContext.slot(4, 6, getSkin());
    }

    private ItemStack getHeadProfile(Player player) {
        return new ItemBuilder(CustomSkull.skullFromName(player.getName()))
                .displayName("<green>" + player.getName())
                .lore(
                        "<white>Primeiro login: <gray>30/10/2024 16:50",
                        "<white>Último login: <gray>31/10/2024 09:23"
                ).build();
    }

    private ItemStack getStats() {
        return new ItemBuilder(Material.PAPER)
                .displayName("<green>Estatísticas")
                .lore("<gray>Veja suas estatísticas.")
                .build();
    }

    private ItemStack getPreferences() {
        return new ItemBuilder(Material.COMPARATOR)
                .displayName("<green>Preferências")
                .lore("<gray>Altere suas preferências.")
                .build();
    }

    private ItemStack getSkin() {
        return new ItemBuilder(Material.ITEM_FRAME)
                .displayName("<green>Sua skin")
                .lore("<gray>Altere sua skin atual.")
                .build();
    }
}
