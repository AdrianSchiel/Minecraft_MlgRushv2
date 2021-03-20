package net.royalbyte.mlgrush.v2.listener;

import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.config.ConfigEntry;
import net.royalbyte.mlgrush.v2.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.UUID;

public class Listener_Quene implements Listener {

    MLGRush instance = MLGRush.getInstance();
    @EventHandler
    public void onQuene(EntityDamageByEntityEvent event){
        try {
            Player player = (Player) event.getDamager();

            if (event.getEntity() instanceof Skeleton) {
                Skeleton s = (Skeleton) event.getEntity();
                if (s.getCustomName().equalsIgnoreCase(ConfigEntry.QUENE.getAsString())) {
                    event.setCancelled(true);
                    if (player.getItemInHand().getType().equals(Material.IRON_SWORD)) {
                        if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7•§8● §bHerausfodern §8●§7•")) {
                            if (instance.getQueneHandler().getList().contains(player.getUniqueId().toString())) {
                                instance.getQueneHandler().getList().remove(player.getUniqueId().toString());
                                player.sendMessage(ConfigEntry.PLAYER_LEAVE_QUENE.getAsString());
                            } else {
                                instance.getQueneHandler().getList().add(player.getUniqueId().toString());
                                player.sendMessage(ConfigEntry.PLAYER_JOIN_QUENE.getAsString());

                                if (instance.getQueneHandler().getList().size() >= 2) {
                                    if (instance.getGameHandler().existFreeArena()) {
                                        Game game = new Game(instance.getGameHandler().getArenas().size() + 1);
                                        game.setPlayer1(player);
                                        game.setPlayer2(Bukkit.getPlayer(UUID.fromString(instance.getQueneHandler().getList().get(0))));
                                        game.setArena(instance.getGameHandler().getNextFreeArena());
                                        game.start();
                                    } else player.sendMessage(ConfigEntry.PLAYER_QUENE_NO_ARENA_FOUND.getAsString());
                                }

                            }
                        }
                    }
                }
            } else if (event.getEntity() instanceof Player) {
                final Player player2 = (Player) event.getEntity();
                if (player.getItemInHand().getType().equals(Material.IRON_SWORD)) {
                    if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7•§8● §bHerausfodern §8●§7•")) {
                        event.setCancelled(true);
                        if (instance.getQueneHandler().getChallenges().containsKey(player.getUniqueId().toString())) {
                            if (instance.getQueneHandler().getChallenges().get(player.getUniqueId().toString()).equalsIgnoreCase(player2.getUniqueId().toString())) {
                                instance.getQueneHandler().getChallenges().remove(player.getUniqueId().toString());
                                player.sendMessage(ConfigEntry.PLAYER_REMOVECHALLENGE.getAsString());
                                return;
                            }

                        }

                        instance.getQueneHandler().getChallenges().put(player.getUniqueId().toString(), player2.getUniqueId().toString());

                        if (instance.getQueneHandler().getChallenges().containsKey(player2.getUniqueId().toString())) {
                            if (instance.getQueneHandler().getChallenges().get(player2.getUniqueId().toString()).equalsIgnoreCase(player.getUniqueId().toString())) {
                                if (instance.getGameHandler().existFreeArena()) {
                                    Game game = new Game(instance.getGameHandler().getArenas().size() + 1);
                                    game.setPlayer1(player);
                                    game.setPlayer2(player2);
                                    game.setArena(instance.getGameHandler().getNextFreeArena());
                                    game.start();
                                } else player.sendMessage(ConfigEntry.PLAYER_QUENE_NO_ARENA_FOUND.getAsString());
                            } else {
                                player.sendMessage(ConfigEntry.PLAYER_CHALLENGE_PLAYER1.getAsString().replaceAll("%PLAYER%", player2.getDisplayName()));
                                player2.sendMessage(ConfigEntry.PLAYER_CHALLENGE_PLAYER2.getAsString().replaceAll("%PLAYER%", player.getDisplayName()));
                            }
                        } else {
                            player.sendMessage(ConfigEntry.PLAYER_CHALLENGE_PLAYER1.getAsString().replaceAll("%PLAYER%", player2.getDisplayName()));
                            player2.sendMessage(ConfigEntry.PLAYER_CHALLENGE_PLAYER2.getAsString().replaceAll("%PLAYER%", player.getDisplayName()));
                        }

                    }
                }
            }

        }catch (Exception e){

        }

    }

}
