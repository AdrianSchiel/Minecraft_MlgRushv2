package net.royalbyte.mlgrush.v2.listener;

import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.game.PlayerGameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class Listener_Lobby implements Listener {

    @EventHandler
    public void onFood(final FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        if(!MLGRush.getInstance().getPlayerGameHandler().getPlayergamestates().get(event.getPlayer().getUniqueId().toString()).equals(PlayerGameState.BUILD)){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPickup(PlayerPickupItemEvent event){
        if(!MLGRush.getInstance().getPlayerGameHandler().getPlayergamestates().get(event.getPlayer().getUniqueId().toString()).equals(PlayerGameState.BUILD)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(EntityDamageEvent event){
        try {
            if (MLGRush.getInstance().getPlayerGameHandler().getPlayergamestates().get(event.getEntity().getUniqueId().toString()).equals(PlayerGameState.LOBBY)) {
                event.setCancelled(true);
            }
        }catch (Exception e){

        }
    }
}
