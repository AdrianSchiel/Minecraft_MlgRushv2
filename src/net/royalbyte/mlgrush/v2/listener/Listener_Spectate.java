package net.royalbyte.mlgrush.v2.listener;

import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.config.ConfigEntry;
import net.royalbyte.mlgrush.v2.game.Game;
import net.royalbyte.mlgrush.v2.game.GameHandler;
import net.royalbyte.mlgrush.v2.game.PlayerGameState;
import net.royalbyte.mlgrush.v2.game.PlayerHandler;
import net.royalbyte.mlgrush.v2.itembuilder.ItemBuilder;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;


public class Listener_Spectate implements Listener {

    @EventHandler
    public void onClick(final PlayerInteractEvent event) {
        try {
            Player player = event.getPlayer();
            if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7•§8● §bSpiel anschauen §8●§7•")) {
                MLGRush.getInstance().getGameHandler().openGameInv(player);
            } else if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7•§8● §bZuschauen beenden §8●§7•")) {
                MLGRush.getInstance().getPlayerGameHandler().getPlayergamestates().put(player.getUniqueId().toString(), PlayerGameState.LOBBY);
                PlayerHandler playerHandler = new PlayerHandler(player);
                playerHandler.teleportSpawn();
                playerHandler.setLobbyItems();
                playerHandler.setScoreboard();
                player.setAllowFlight(false);
                player.setFlying(false);
                player.setGameMode(GameMode.ADVENTURE);
                for (Game game : MLGRush.getInstance().getGameHandler().getGames())
                    if (game.getSpectator().contains(player.getUniqueId().toString())) {
                        game.getSpectator().remove(player.getUniqueId().toString());
                        game.getPlayer2().showPlayer(player);
                        game.getPlayer1().showPlayer(player);
                        player.sendMessage(ConfigEntry.SPECTATE_LEAVE.getAsString().replaceAll("%ID%", String.valueOf(game.getID())));
                    }

            }
        } catch (Exception e) {

        }
    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        try {
            Player player = (Player) event.getWhoClicked();
            if (event.getInventory().getName().equalsIgnoreCase("§7•§8● §bSpiele §8●§7•")) {
                event.setCancelled(true);
                String name = event.getCurrentItem().getItemMeta().getDisplayName();
                GameHandler gameHandler = MLGRush.getInstance().getGameHandler();
                for (Game game : gameHandler.getGames()) {
                    if (game.getArena().getDisplayname().replaceAll("&", "§").equalsIgnoreCase(name)) {
                        String mapname = event.getCurrentItem().getItemMeta().getLore().get(3);
                        mapname = mapname.replaceAll("§7» Map: ", "");
                        if (game.getArena().getName().equalsIgnoreCase(mapname)) {
                            player.teleport(game.getArena().getSpawn1());
                            game.getPlayer1().hidePlayer(player);
                            game.getPlayer2().hidePlayer(player);
                            game.getSpectator().add(player.getUniqueId().toString());
                            MLGRush.getInstance().getPlayerGameHandler().getPlayergamestates().put(player.getUniqueId().toString(), PlayerGameState.SPECTATE);
                            player.setAllowFlight(true);
                            player.setFlying(true);
                            player.getInventory().clear();
                            player.getInventory().setArmorContents(null);
                            player.getInventory().setItem(4, new ItemBuilder(Material.SLIME_BALL, 1).setName("§7•§8● §bZuschauen beenden §8●§7•").build());
                            player.sendMessage(ConfigEntry.SPECTATE_NOTIFY.getAsString().replaceAll("%ID%", String.valueOf(game.getID())));
                            new PlayerHandler(player).setScoreboard();
                        } else player.sendMessage(ConfigEntry.SPECTATE_GAMENOTFOUND.getAsString());
                    } else player.sendMessage(ConfigEntry.SPECTATE_GAMENOTFOUND.getAsString());
                }
            }
        } catch (Exception e) {

        }
    }

    @EventHandler
    public void onDMG2(final EntityDamageEvent event) {
        try {
            Player player = (Player) event.getEntity();
            if (MLGRush.getInstance().getPlayerGameHandler().getPlayergamestates().get(player.getUniqueId().toString()).equals(PlayerGameState.SPECTATE))
                event.setCancelled(true);
        } catch (Exception e) {

        }

    }

    @EventHandler
    public void onDMG(final EntityDamageByEntityEvent event) {
        try {
            Player player = (Player) event.getDamager();
            if (MLGRush.getInstance().getPlayerGameHandler().getPlayergamestates().get(player.getUniqueId().toString()).equals(PlayerGameState.SPECTATE))
                event.setCancelled(true);

        } catch (Exception e) {
        }

    }

}
