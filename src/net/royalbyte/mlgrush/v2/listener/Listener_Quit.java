package net.royalbyte.mlgrush.v2.listener;

import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.config.ConfigEntry;
import net.royalbyte.mlgrush.v2.game.Game;
import net.royalbyte.mlgrush.v2.game.PlayerGameState;
import net.royalbyte.mlgrush.v2.game.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Listener_Quit implements Listener {


    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();

        PlayerHandler playerHandler = new PlayerHandler(player);
        if(MLGRush.getInstance().getPlayerGameHandler().getPlayergamestates().get(player.getUniqueId().toString()).equals(PlayerGameState.INGAME)){
            Game game = playerHandler.getGame();
            if(game.getPlayer1() == player) game.win(game.getPlayer2());
            else if (game.getPlayer2() == player) game.win(game.getPlayer1());
        }

        if (MLGRush.getInstance().getPlayerGameHandler().getPlayergamestates().containsKey(player.getUniqueId().toString()))
            MLGRush.getInstance().getPlayerGameHandler().getPlayergamestates().remove(player.getUniqueId().toString());

        if(MLGRush.getInstance().getQueneHandler().getList().contains(player.getUniqueId().toString()))
            MLGRush.getInstance().getQueneHandler().getList().remove(player.getUniqueId().toString());



        new BukkitRunnable(){
            @Override
            public void run() {
                for(Player all : Bukkit.getOnlinePlayers()){
                    new PlayerHandler(all).setScoreboard();
                    if(MLGRush.getInstance().getPlayerGameHandler().getPlayergamestates().get(all.getUniqueId().toString()).equals(PlayerGameState.LOBBY)){
                        all.sendMessage(ConfigEntry.PLAYER_QUIT.getAsString().replaceAll("%PLAYER%", player.getDisplayName()));
                    }
                }
            }
        }.runTaskLaterAsynchronously(MLGRush.getInstance(), 10);


    }
}
