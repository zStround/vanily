package com.github.vanily.essentials.listener;

import com.github.vanily.core.serializer.CustomLocation;
import com.github.vanily.essentials.EssentialsPlugin;
import com.google.common.collect.Sets;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Set;

import static org.bukkit.Material.AIR;

@RequiredArgsConstructor
public class PlayerListener implements Listener {

    private final Set<Integer> crafts = Sets.newHashSet(Arrays.asList(333, 167, 324, 330, 427, 428, 429, 430, 431, 33, 183, 184, 84, 185, 186, 187, 157, 96, 321, 416, 28, 328, 138, 398, 23, 380, 29, 346, 389, 420, 46, 158, 154, 107, 131, 356, 407, 408,404, 27, 66, 343, 342, 329, 420, 421, 345, 346, 259, 145));

    private final EssentialsPlugin plugin;

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setFoodLevel(20);
        event.setCancelled(true);
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        final Player player = event.getPlayer();

        final CustomLocation customLocation = CustomLocation.of(plugin.getConfig().getString("locations.spawn"));
        final Location location = customLocation.getLocation();

        player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 50));
        player.playSound(location, Sound.ENTITY_WITHER_SPAWN, 2.0F, 2.0F);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerKick(PlayerKickEvent event) {
        event.setLeaveMessage(null);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);

        final Player player = event.getEntity();

        if (player.hasPermission("essentials.vip")) {
            event.setKeepLevel(true);
            event.setDroppedExp(0);
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPrepareItemCraft(PrepareItemCraftEvent event) {
        final Recipe recipe = event.getRecipe();

        if (recipe == null) return;

        int item = event.getRecipe().getResult().getType().getId();

        if (this.crafts.contains(item))
            event.getInventory().setResult(new ItemStack(AIR));

    }
}
