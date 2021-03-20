package net.royalbyte.mlgrush.v2.listener;

import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.config.ConfigEntry;
import net.royalbyte.mlgrush.v2.game.PlayerGameState;
import net.royalbyte.mlgrush.v2.game.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listener_Join implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent event){
        event.setJoinMessage(null);

        Player player = event.getPlayer();
        MLGRush.getInstance().getPlayerGameHandler().getPlayergamestates().put(player.getUniqueId().toString(), PlayerGameState.LOBBY);
        PlayerHandler playerHandler = new PlayerHandler(player);
        playerHandler.getStats().addPlayer(player.getName());
        playerHandler.getPlayerInv().addPlayer(player.getName());
        playerHandler.teleportSpawn();
        playerHandler.setLobbyItems();
       // playerHandler.addtoPlayerGameInv();

        for(Player all : Bukkit.getOnlinePlayers()){
            new PlayerHandler(all).setScoreboard();
            if(MLGRush.getInstance().getPlayerGameHandler().getPlayergamestates().get(all.getUniqueId().toString()).equals(PlayerGameState.LOBBY)){
                all.sendMessage(ConfigEntry.PLAYER_JOIN.getAsString().replaceAll("%PLAYER%", player.getDisplayName()));
            }

        }


    }

}
