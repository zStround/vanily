package com.github.vanily.scoreboard;

import com.github.vanily.scoreboard.listeners.PlayerListeners;
import fr.mrmicky.fastboard.FastBoard;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class ScoreBoardPlugin extends JavaPlugin {
    private final Map<UUID, FastBoard> boards = new HashMap<>();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        this.getServer().getPluginManager().registerEvents(new PlayerListeners(), this);

        this.getServer().getScheduler().runTaskTimer(this, () -> {
            for (FastBoard board : this.boards.values()) {
                updateBoard(board);
            }
        }, 0, getConfig().getLong("scoreboard.delay"));
    }

    private void updateBoard(FastBoard board) {
        board.updateLines(
                getConfig().getStringList("scoreboard.lines")
        );
    }

    public static ScoreBoardPlugin getInstance() {
        return getPlugin(ScoreBoardPlugin.class);
    }
}
