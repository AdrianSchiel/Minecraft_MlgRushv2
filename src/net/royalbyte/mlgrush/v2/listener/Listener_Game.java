package net.royalbyte.mlgrush.v2.listener;

import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.config.ConfigEntry;
import net.royalbyte.mlgrush.v2.game.Game;
import net.royalbyte.mlgrush.v2.game.PlayerGameState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class Listener_Game implements Listener {

    MLGRush instance = MLGRush.getInstance();


    @EventHandler
    public void onDMG(final EntityDamageEvent event){
        if(event.getEntity() instanceof  Player){
            Player player = (Player) event.getEntity();
                if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                    event.setCancelled(true);
                }
        }
    }

    @EventHandler
    public void onBuild(final BlockPlaceEvent event) {
        PlayerGameState gameState = instance.getPlayerGameHandler().getPlayergamestates().get(event.getPlayer().getUniqueId().toString());
        if (gameState.equals(PlayerGameState.LOBBY)) {
            event.setCancelled(true);
        } else if (gameState.equals(PlayerGameState.INGAME)) {
            Game game = instance.getGameHandler().getGamefromPlayer(event.getPlayer());

            if(event.getBlock().getY()>= game.getArena().getMaxhigh()){
                event.setCancelled(true);
            }

            game.getPlaced_blocks().add(event.getBlock());

        }
    }

    @EventHandler
    public void onBreak(final BlockBreakEvent event) {
        final Player player = event.getPlayer();
        PlayerGameState gameState = instance.getPlayerGameHandler().getPlayergamestates().get(player.getUniqueId().toString());
        if (gameState.equals(PlayerGameState.INGAME)) {
            Game game = instance.getGameHandler().getGamefromPlayer(player);

            if(!game.getPlaced_blocks().contains(event.getBlock())){
                event.setCancelled(true);
            }

            int x = event.getBlock().getLocation().getBlockX();
            int y = event.getBlock().getLocation().getBlockY();
            int z = event.getBlock().getLocation().getBlockZ();
            if (event.getBlock().getType().equals(Material.BED_BLOCK)) {
                event.setCancelled(true);
                for (Location location : game.getBed_locs()) {
                    if (location.getBlockX() == x && location.getBlockY() == y && location.getBlockZ() == z) {
                        if (game.getPlayer1().equals(player)) {
                            if (location == game.getArena().getBed1_back() || location == game.getArena().getBed1_front())
                                player.sendMessage(ConfigEntry.GAME_OWNBED.getAsString());
                            else game.reset(player);
                        } else if (game.getPlayer2().equals(player)) {
                            if (location == game.getArena().getBed2_back() || location == game.getArena().getBed2_front())
                                player.sendMessage(ConfigEntry.GAME_OWNBED.getAsString());
                            else game.reset(player);
                        }
                    }
                }
            }

        }else if(gameState.equals(PlayerGameState.SPECTATE)){
            event.setCancelled(true);
        } else if(gameState.equals(PlayerGameState.LOBBY)){
            event.setCancelled(true);
        }
    }


}
