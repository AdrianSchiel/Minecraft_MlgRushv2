package net.royalbyte.mlgrush.v2.game;

import net.royalbyte.mlgrush.v2.MLGRush;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerGameHandler {

    private MLGRush instance;

    private Map<String, PlayerGameState> playergamestates;

    public PlayerGameHandler(MLGRush mlgRush) {
        this.instance = mlgRush;
        this.playergamestates = new HashMap<>();
        loadPlayerGameStates();
    }



    private void loadPlayerGameStates() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            getPlayergamestates().put(player.getUniqueId().toString(), PlayerGameState.LOBBY);
            PlayerHandler playerHandler = new PlayerHandler(player);
            playerHandler.teleportSpawn();
            playerHandler.setLobbyItems();

        }
    }


    public Map<String, PlayerGameState> getPlayergamestates() {
        return playergamestates;
    }
}
