package com.github.vanily.lobby.listeners;

import com.github.vanily.lobby.LobbyPlugin;
import com.github.vanily.lobby.commands.BuildCommand;
import com.github.vanily.lobby.view.LobbySelectorView;
import com.github.vanily.lobby.view.ProfileView;
import com.github.vanily.lobby.view.SelectorView;
import com.google.common.collect.Maps;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.github.vanily.core.color.ColorUtil.*;
import static com.github.vanily.lobby.constants.MessageConstants.*;
import static com.github.vanily.lobby.constants.ItemConstants.*;

public class PlayerListener implements Listener {
    public static List<Player> isHidden = new ArrayList<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        final Player player = event.getPlayer();

        player.getInventory().clear();
        player.getInventory().setItem(0, SELECTOR.build());
        player.getInventory().setItem(1, PROFILE.build());
        player.getInventory().setItem(4, COSMETICS.build());

        if (isHidden.contains(player)) {
            player.getInventory().setItem(7, PLAYERS_OFF.build());
        } else {
            player.getInventory().setItem(7, PLAYERS_ON.build());
        }

        player.getInventory().setItem(8, LOBBY.build());

        player.showTitle(
                Title.title(colored(WELCOME_TITLE), colored(WELCOME_SUBTITLE))
        );

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if (event.getItem() == null) return;
        if (event.getItem().equals(SELECTOR.build())) {
            LobbyPlugin.getInstance().getViewFrame().open(SelectorView.class, player);
        }

        if (event.getItem().equals(PROFILE.build())) {
            LobbyPlugin.getInstance().getViewFrame().open(ProfileView.class, player);
        }

        if (event.getItem().equals(PLAYERS_ON.build())) {
            isHidden.add(player);

            for (Player players : Bukkit.getOnlinePlayers())
                if (!players.hasPermission("vanily.admin"))
                    player.hidePlayer(LobbyPlugin.getInstance(), players);

            player.getInventory().setItem(7, PLAYERS_OFF.build());
            player.sendMessage(colored(PLAYERS_DISABLED));
        } else if (event.getItem().equals(PLAYERS_OFF.build())) {
            isHidden.remove(player);

            for (Player players : Bukkit.getOnlinePlayers())
                player.showPlayer(LobbyPlugin.getInstance(), players);

            player.getInventory().setItem(7, PLAYERS_ON.build());
            player.sendMessage(colored(PLAYERS_ACTIVED));
        }

        if (event.getItem().equals(LOBBY.build())) {
            LobbyPlugin.getInstance().getViewFrame().open(LobbySelectorView.class, player);
        }
    }


    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        final Player player = (Player) event.getWhoClicked();

        if (!(event.getInventory().getType() == InventoryType.PLAYER)) return;
        if (!BuildCommand.builders.contains(player.getName())) {
            event.setCancelled(true);

            if (player.hasPermission("vanily.admin")) {
                player.sendActionBar(colored(BUILDER_ACTIONBAR));
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!BuildCommand.builders.contains(event.getPlayer().getName())) {
            event.setCancelled(true);

            if (event.getPlayer().hasPermission("vanily.admin")) {
                event.getPlayer().sendActionBar(colored(BUILDER_ACTIONBAR));
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!BuildCommand.builders.contains(event.getPlayer().getName())) {
            event.setCancelled(true);

            if (event.getPlayer().hasPermission("vanily.admin")) {
                event.getPlayer().sendActionBar(colored(BUILDER_ACTIONBAR));
            }
        }
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

}
