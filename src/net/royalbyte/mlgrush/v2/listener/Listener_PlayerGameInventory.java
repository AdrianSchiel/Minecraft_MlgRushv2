package net.royalbyte.mlgrush.v2.listener;

import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.config.ConfigEntry;
import net.royalbyte.mlgrush.v2.game.PlayerGameState;
import net.royalbyte.mlgrush.v2.game.PlayerHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Listener_PlayerGameInventory implements Listener {

    @EventHandler
    public void onInt(final PlayerInteractEvent event){
        try{
            Player player = event.getPlayer();
            if(player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7•§8● §bInventar bearbeiten §8●§7•")){
                event.setCancelled(true);
                new PlayerHandler(player).getPlayerInv().openInventory();
            }
        }catch (Exception e){
        }
    }

    @EventHandler
    public void onDrop(final PlayerDropItemEvent event){
        if(!MLGRush.getInstance().getPlayerGameHandler().getPlayergamestates().get(event.getPlayer().getUniqueId().toString()).equals(PlayerGameState.BUILD)){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClose(final InventoryCloseEvent event){
        try {
            Player player = (Player)event.getPlayer();
            PlayerHandler playerHandler = new PlayerHandler(player);
            if (event.getInventory().getName().equalsIgnoreCase("§7•§8● §bInventar §8●§7•")) {
                int slot = 0;
                for(ItemStack itemStack : event.getInventory().getContents()){
                    int id = Integer.valueOf(itemStack.getItemMeta().getLore().get(0).replaceAll("§7ID: ", ""));
                    playerHandler.getPlayerInv().setSlot(slot+1, id);
                    slot++;
                }
            player.sendMessage(ConfigEntry.PLAYER_INV_SAVE.getAsString());
        }
    } catch (Exception exception) {}
    }

}
