package net.royalbyte.mlgrush.v2.game;

import net.minecraft.server.v1_8_R3.*;
import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.itembuilder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PlayerHandler {

    private Player player;
    private MLGRush instance;

    public PlayerHandler(Player player) {
        this.player = player;
        this.instance = MLGRush.getInstance();
    }

    public PlayerStats getStats() {
        return new PlayerStats(this.player);
    }


    public void teleportSpawn() {
        player.teleport(instance.getLocationHandler().getMap().get("spawn").getLocation());
    }

    public void changePlayerGameState(final PlayerGameState state) {
        instance.getPlayerGameHandler().getPlayergamestates().put(player.getUniqueId().toString(), state);
    }


    public boolean inGame() {
        return instance.getPlayerGameHandler().getPlayergamestates().get(player.getUniqueId().toString()).equals(PlayerGameState.INGAME);
    }

    public PlayerGameInv getPlayerInv() {
        return new PlayerGameInv(this.player);
    }

    public Game getGame() {
        if (inGame()) return instance.getGameHandler().getGamefromPlayer(player);
        else return null;
    }


    public void setScoreboard() {
        PlayerGameState state = instance.getPlayerGameHandler().getPlayergamestates().get(player.getUniqueId().toString());
        if (state.equals(PlayerGameState.INGAME) || state.equals(PlayerGameState.SPECTATE)) {
            Game game = null;
            if (state.equals(PlayerGameState.INGAME))
                game = instance.getGameHandler().getGamefromPlayer(player);
            else
                for (Game games : instance.getGameHandler().getGames())
                    if (games.getSpectator().contains(player.getUniqueId().toString()))
                        game = games;

            Scoreboard sb = new Scoreboard();
            ScoreboardObjective obj = sb.registerObjective(instance.getScoreboardConfig().getDisplayname_ingame().replaceAll("&", "§"), IScoreboardCriteria.b);
            PacketPlayOutScoreboardObjective createpacket = new PacketPlayOutScoreboardObjective(obj, 0);
            PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
            PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
            obj.setDisplayName("INGAME");

            sendPacket(removePacket);
            sendPacket(createpacket);
            sendPacket(display);
            int scoreid = 0;
            for (String score : instance.getScoreboardConfig().getIngame()) {

                score = score.replaceAll("%USERNAME%", player.getDisplayName());
                score = score.replaceAll("%ONLINEPLAYERS%", String.valueOf(Bukkit.getOnlinePlayers().size()));
                score = score.replaceAll("%LOOSES%", String.valueOf(getStats().getLoses()));
                score = score.replaceAll("%WINS%", String.valueOf(getStats().getWins()));
                score = score.replaceAll("%ARENANAME%", game.getArena().getDisplayname());
                score = score.replaceAll("%TEAMBLUEPOINTS%", String.valueOf(game.getPoints().get(game.getPlayer2().getUniqueId().toString())));
                score = score.replaceAll("%TEAMREDPOINTS%", String.valueOf(game.getPoints().get(game.getPlayer1().getUniqueId().toString())));

                scoreid++;
                ScoreboardScore s = new ScoreboardScore(sb, obj, score.replaceAll("&", "§"));
                s.setScore(scoreid);
                PacketPlayOutScoreboardScore pa = new PacketPlayOutScoreboardScore(s);
                sendPacket(pa);
            }
        } else {
            Scoreboard sb = new Scoreboard();
            ScoreboardObjective obj = sb.registerObjective(instance.getScoreboardConfig().getDisplayname_lobby().replaceAll("&", "§"), IScoreboardCriteria.b);
            PacketPlayOutScoreboardObjective createpacket = new PacketPlayOutScoreboardObjective(obj, 0);
            PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
            PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
            obj.setDisplayName("LOBBY");

            sendPacket(removePacket);
            sendPacket(createpacket);
            sendPacket(display);
            int scoreid = 0;
            for (String score : instance.getScoreboardConfig().getLobby()) {

                score = score.replaceAll("%USERNAME%", player.getDisplayName());
                score = score.replaceAll("%ONLINEPLAYERS%", String.valueOf(Bukkit.getOnlinePlayers().size()));
                score = score.replaceAll("%LOOSES%", String.valueOf(getStats().getLoses()));
                score = score.replaceAll("%WINS%", String.valueOf(getStats().getWins()));

                scoreid++;
                ScoreboardScore s = new ScoreboardScore(sb, obj, score.replaceAll("&", "§"));
                s.setScore(scoreid);
                PacketPlayOutScoreboardScore pa = new PacketPlayOutScoreboardScore(s);
                sendPacket(pa);
            }
        }
    }

    public void sendPacket(Packet<?> packet) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    public void setLobbyItems() {
        player.setLevel(0);
        player.setFoodLevel(20);
        player.setMaxHealth(20);
        player.setHealth(20);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD, 1).setName("§7•§8● §bHerausfodern §8●§7•").build());
        player.getInventory().setItem(1, new ItemBuilder(Material.REDSTONE_COMPARATOR, 1).setName("§7•§8● §bInventar bearbeiten §8●§7•").build());
        player.getInventory().setItem(8, new ItemBuilder(Material.MAGMA_CREAM, 1).setName("§7•§8● §bStats §8●§7•").build());
        player.getInventory().setItem(7, new ItemBuilder(Material.SIGN, 1).setName("§7•§8● §bSpiel anschauen §8●§7•").build());


    }


}
