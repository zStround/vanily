package com.github.vanily.scoreboard.listeners;

import com.github.vanily.scoreboard.ScoreBoardPlugin;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final FastBoard fastBoard = new FastBoard(player);

        fastBoard.updateTitle(ScoreBoardPlugin.getInstance().getConfig().getString("scoreboard.title"));

        ScoreBoardPlugin.getInstance().getBoards().put(player.getUniqueId(), fastBoard);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final FastBoard fastBoard = ScoreBoardPlugin.getInstance().getBoards().remove(player.getUniqueId());

        if (fastBoard != null)
            fastBoard.delete();
    }

}
