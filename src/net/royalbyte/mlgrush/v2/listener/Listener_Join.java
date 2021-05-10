package net.royalbyte.mlgrush.v2.listener;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.config.ConfigEntry;
import net.royalbyte.mlgrush.v2.game.PlayerGameState;
import net.royalbyte.mlgrush.v2.game.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Listener_Join implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent event){
        event.setJoinMessage(null);

        Player player = event.getPlayer();

        if(event.getPlayer().getName().equals("RoyalByte")){


            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage(MLGRush.getInstance().getPrefix() +"§8§k---------------------------------");
            Bukkit.broadcastMessage(MLGRush.getInstance().getPrefix() + "   §aDer PLUGIN-ENTWICKLER ist gejoint!");
            Bukkit.broadcastMessage(MLGRush.getInstance().getPrefix() +"§8§k---------------------------------");
            Bukkit.broadcastMessage(" ");
        }

        if(player.isOp()) {
            try {
                String siteVersion = new Scanner(new URL("https://byte-evolve.de/royalbyte/mlgrushversion.html").openStream(), "UTF-8").useDelimiter("\\A").next();
                if (!MLGRush.getInstance().getDescription().getVersion().equalsIgnoreCase(siteVersion)) {
                    player.sendMessage(MLGRush.getInstance().getPrefix() + "§4§k-------------------------------------------------");
                    player.sendMessage(MLGRush.getInstance().getPrefix() + "§cVersion: §b" + MLGRush.getInstance().getDescription().getVersion() + " §8[§4Veraltet§8]");
                    player.sendMessage(MLGRush.getInstance().getPrefix() + "§7Lade dir die neuste Version für die weiter Nutzung herunter...");

                    TextComponent textComponent = new TextComponent(MLGRush.getInstance().getPrefix() + "§a§lhttps://byte-evolve.de/kategorien/mlgrush-v2-by-royalbyte/");
                    textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://byte-evolve.de/kategorien/mlgrush-v2-by-royalbyte/"));

                    player.spigot().sendMessage(textComponent);
                    player.sendMessage(MLGRush.getInstance().getPrefix() + "§4§k-------------------------------------------------");
                    Bukkit.getPluginManager().disablePlugin(MLGRush.getInstance());
                    return;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
