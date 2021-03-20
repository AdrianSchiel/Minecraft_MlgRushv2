package net.royalbyte.mlgrush.v2.listener;

import net.royalbyte.mlgrush.v2.config.ConfigEntry;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class Listener_EntityDamage implements Listener {

    @EventHandler
    public void onDMG(final EntityDamageEvent event){
        try {
            if (event.getEntity() instanceof Skeleton) {
                if (event.getEntity().getCustomName().equalsIgnoreCase(ConfigEntry.QUENE.getAsString())) {
                    event.setCancelled(true);
                }
            }
        }catch (Exception e){
        }
    }
}
