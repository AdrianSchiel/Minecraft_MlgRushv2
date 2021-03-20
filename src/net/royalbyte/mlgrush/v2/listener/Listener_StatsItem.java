package net.royalbyte.mlgrush.v2.listener;

import net.royalbyte.mlgrush.v2.game.PlayerHandler;
import net.royalbyte.mlgrush.v2.itembuilder.ItemBuilder;
import net.royalbyte.mlgrush.v2.itembuilder.SkullBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class Listener_StatsItem implements Listener {

    @EventHandler
    public void onInterract(final PlayerInteractEvent e) {
        try {
            final Player p = e.getPlayer();
            if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7•§8● §bStats §8●§7•")) {
                openStats(p);
                e.setCancelled(true);
            }

        } catch (Exception e2) {
        }
    }

    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        try {
            if (e.getClickedInventory().getName().equalsIgnoreCase("§7•§8● §bStats §8●§7•")) {
                e.setCancelled(true);
            }
        } catch (Exception e2) {
        }
    }

    private void openStats(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "§7•§8● §bStats §8●§7•");
        PlayerHandler playerHandler = new PlayerHandler(p);
        inv.setItem(4, new SkullBuilder(p.getName(), 1).setName("§7•§8● §b" + p.getName() + " §8●§7•").build());
        inv.setItem(10, new ItemBuilder( Material.GOLD_NUGGET, 1).setName("§7•§8● §eWins §8●§7•").addLore("§8§M§l-------------------").addLore("§7» Deine Wins: §e" + playerHandler.getStats().getWins()).addLore("§8§M§l-------------------").build());
        inv.setItem(12, new ItemBuilder(Material.PAPER, 1).setName("§7•§8● §9Points §8●§7•").addLore("§8§M§l-------------------").addLore("§7» Deine Points: §9" + playerHandler.getStats().getPoints()).addLore("§8§M§l-------------------").build());
        inv.setItem(14, new ItemBuilder(Material.RED_ROSE, 1).setName("§7•§8● §aGames §8●§7•").addLore("§8§M§l-------------------").addLore("§7» Deine Games: §a" + playerHandler.getStats().getGames()).addLore("§8§M§l-------------------").build());
        inv.setItem(16, new ItemBuilder(Material.BARRIER, 1).setName("§7•§8● §cLooses §8●§7•").addLore("§8§M§l-------------------").addLore("§7» Deine Looses: §c" + playerHandler.getStats().getLoses()).addLore("§8§M§l-------------------").build());
        p.openInventory(inv);
    }

}
