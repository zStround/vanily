package com.github.vanily.lobby.constants;

import com.github.vanily.core.item.CustomSkull;
import com.github.vanily.core.item.ItemBuilder;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

@UtilityClass
public class ItemConstants {
    public ItemBuilder SELECTOR = new ItemBuilder(Material.RECOVERY_COMPASS)
            .displayName("<green>Seletor de modo");

    public ItemBuilder PROFILE = new ItemBuilder(Material.PLAYER_HEAD)
            .displayName("<green>Seu perfil");

    public ItemBuilder COSMETICS = new ItemBuilder(Material.CHEST_MINECART)
            .displayName("<green>Cosméticos");

    public ItemBuilder PLAYERS_ON = new ItemBuilder(Material.LIME_DYE)
            .displayName("<green>Jogadores ativados");

    public ItemBuilder PLAYERS_OFF = new ItemBuilder(Material.GRAY_DYE)
            .displayName("<red>Jogadores desativados");

    public ItemBuilder LOBBY = new ItemBuilder(Material.NETHER_STAR)
            .displayName("<green>Seletor de lobby");



    // MENU SELECTOR

    public ItemBuilder MENU_SELECTOR_LOBBY = new ItemBuilder(Material.BOOKSHELF)
            .displayName("<green>Lobby Principal")
            .lore("<green>Clique para conectar!");

    public ItemBuilder MENU_SELECTOR_BEDWARS = new ItemBuilder(Material.GREEN_BED)
            .displayName("<green><#07f246>Bedwars <#faff63><bold>NEW*")
            .lore(
                    "",
                    " <gray>Forme sua equipe, defenda sua cama e",
                    " <gray>destrua as dos inimigos para vencer!",
                    "",
                    " <#2e2f30>▪ Solo;",
                    " <#2e2f30>▪ Dupla;",
                    " <#2e2f30>▪ Trio;",
                    " <#2e2f30>▪ Quarteto;",
                    " <#2e2f30>... e muito mais!",
                    "",
                    "<#07f246>Clique para conectar!",
                    "<gray>1.890 jogando."
            ).itemFlags(ItemFlag.HIDE_ATTRIBUTES);

    public ItemBuilder MENU_SELECTOR_PVP = new ItemBuilder(Material.IRON_CHESTPLATE)
            .displayName("<green><#07f246>PvP <#faff63><bold>NEW*")
            .lore(
                    "",
                    " <gray>Entre na arena solo ou com amigos e",
                    " <gray>mostre seu domínio nas batalhas de PvP!",
                    "",
                    " <#2e2f30>▪ Arena;",
                    " <#2e2f30>▪ FPS;",
                    " <#2e2f30>▪ MLG;",
                    " <#2e2f30>▪ Lava;",
                    " <#2e2f30>... e muito mais!",
                    "",
                    "<#07f246>Clique para conectar!",
                    "<gray>1.890 jogando."
            ).itemFlags(ItemFlag.HIDE_ATTRIBUTES);

    public ItemBuilder MENU_SELECTOR_SKYWARS = new ItemBuilder(Material.ENDER_EYE)
            .displayName("<green><#07f246>Skywars <#faff63><bold>NEW*")
            .lore(
                    "",
                    " <gray>Lute solo ou em equipe nas ilhas flutuantes",
                    " <gray>e derrote todos para alcançar a vitória!",
                    "",
                    " <#2e2f30>▪ Ranqueado;",
                    " <#2e2f30>▪ Clássico;",
                    " <#2e2f30>▪ Solo;",
                    " <#2e2f30>▪ Dupla;",
                    " <#2e2f30>... e muito mais!",
                    "",
                    "<#07f246>Clique para conectar!",
                    "<gray>1.890 jogando."
            ).itemFlags(ItemFlag.HIDE_ATTRIBUTES);

    public ItemBuilder MENU_SELECTOR_RANKUP = new ItemBuilder(Material.DIAMOND_PICKAXE)
            .displayName("<green><#07f246>RankUP <#faff63><bold>NEW*")
            .lore(
                    "",
                    " <gray>Jogue solo ou junte-se ao seu clã para",
                    " <gray>dominar esta experiência no farm e PvP!",
                    "",
                    " <#2e2f30>▪ Pescaria Exclusiva;",
                    " <#2e2f30>▪ Caixas Misteriosas;",
                    " <#2e2f30>▪ Spawners Poderosos;",
                    " <#2e2f30>▪ Máquinas Avançadas;",
                    " <#2e2f30>▪ Pets Companheiros;",
                    " <#2e2f30>... e muito mais!",
                    "",
                    "<#07f246>Clique para conectar!",
                    "<gray>779 jogando."
            ).itemFlags(ItemFlag.HIDE_ATTRIBUTES);

    public ItemBuilder MENU_SELECTOR_NEXUS = new ItemBuilder(Material.END_CRYSTAL)
            .displayName("<green><#07f246>Nexus <#faff63><bold>NEW*")
            .lore(
                    "",
                    " <gray>Monte seu clã, defenda seu Nexus e",
                    " <gray>destrua os dos inimigos para dominar!",
                    "",
                    " <#2e2f30>▪ Mineração Inovadora;",
                    " <#2e2f30>▪ Caixas Misteriosas;",
                    " <#2e2f30>▪ Spawners Poderosos;",
                    " <#2e2f30>▪ Evolução de Nexus;",
                    " <#2e2f30>▪ Sistema de Clãs;",
                    " <#2e2f30>... e muito mais!",
                    "",
                    "<#07f246>Clique para conectar!",
                    "<gray>779 jogando."
            ).itemFlags(ItemFlag.HIDE_ATTRIBUTES);

}
