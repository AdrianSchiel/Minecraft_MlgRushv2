package net.royalbyte.mlgrush.v2.game;

import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.arena.Arena;
import net.royalbyte.mlgrush.v2.config.ConfigEntry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.*;

public class Game {

    private int ID;
    private Player player1, player2;
    private Arena arena;
    private Map<String, Integer> points;
    private List<Location> bed_locs;
    private List<Block> placed_blocks;
    private List<String> spectator;

    MLGRush instance;


    public Game(int ID) {
        this.ID = ID;
        this.points = new HashMap<>();
        this.instance = MLGRush.getInstance();
        this.bed_locs = new ArrayList<>();
        this.placed_blocks = new ArrayList<>();
        this.spectator = new ArrayList<>();
    }

    public void loadBed_Locs() {
        bed_locs.clear();
        bed_locs.add(arena.getBed1_back());
        bed_locs.add(arena.getBed1_front());
        bed_locs.add(arena.getBed2_back());
        bed_locs.add(arena.getBed2_front());
    }

    public List<String> getSpectator() {
        return spectator;
    }

    public void resetBlocks() {
        for (Block block : placed_blocks) {
            block.setType(Material.AIR);
        }
    }

    public void start() {

        instance.getGameHandler().getGames().add(this);
        instance.getGameHandler().getArenas().add(this.arena);
        instance.getQueneHandler().getList().remove(player1.getUniqueId().toString());
        instance.getQueneHandler().getList().remove(player2.getUniqueId().toString());
        loadBed_Locs();

        MLGRush.getInstance().getPlayerGameHandler().getPlayergamestates().put(player1.getUniqueId().toString(), PlayerGameState.INGAME);
        MLGRush.getInstance().getPlayerGameHandler().getPlayergamestates().put(player2.getUniqueId().toString(), PlayerGameState.INGAME);

        this.points.put(this.player1.getUniqueId().toString(), 0);
        this.points.put(this.player2.getUniqueId().toString(), 0);

        this.player1.teleport(arena.getSpawn1());
        this.player2.teleport(arena.getSpawn2());

        PlayerHandler playerHandler1 = new PlayerHandler(player1);
        PlayerHandler playerHandler2 = new PlayerHandler(player2);

        playerHandler1.setScoreboard();
        playerHandler2.setScoreboard();

        playerHandler1.getPlayerInv().setInventory();
        playerHandler2.getPlayerInv().setInventory();

        player1.sendTitle(ConfigEntry.GAME_START_TITLE1.getAsString().replaceAll("%TEAM%", "§cRot"), ConfigEntry.GAME_START_TITLE2.getAsString().replaceAll("%TEAM%", "§cRot"));
        player2.sendTitle(ConfigEntry.GAME_START_TITLE1.getAsString().replaceAll("%TEAM%", "§9Blau"), ConfigEntry.GAME_START_TITLE2.getAsString().replaceAll("%TEAM%", "§9Blau"));

    }

    public void reset(Player player) {

        int points = getPoints().get(player.getUniqueId().toString());
        points++;
        getPoints().remove(player.getUniqueId().toString());
        getPoints().put(player.getUniqueId().toString(), points);

        resetBlocks();

        if (points < ConfigEntry.GAME_WIN_POINTS.getAsInteger()) {
            this.player1.teleport(arena.getSpawn1());
            this.player2.teleport(arena.getSpawn2());

            PlayerHandler playerHandler1 = new PlayerHandler(player1);
            PlayerHandler playerHandler2 = new PlayerHandler(player2);

            for (String uuid : getSpectator()) {
                Player player3 = Bukkit.getPlayer(UUID.fromString(uuid));
                PlayerHandler playerHandler = new PlayerHandler(player3);
                playerHandler.setScoreboard();
                if (player == player1) {
                    player3.sendTitle(ConfigEntry.GAME_POINT_TITLE1.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§c"), ConfigEntry.GAME_POINT_TITLE2.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§c"));
                } else if (player == player2) {
                    player3.sendTitle(ConfigEntry.GAME_POINT_TITLE1.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§9"), ConfigEntry.GAME_POINT_TITLE2.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§9"));
                }
            }

            playerHandler1.setScoreboard();
            playerHandler2.setScoreboard();

            playerHandler1.getPlayerInv().setInventory();
            playerHandler2.getPlayerInv().setInventory();

            if (player == player1) {
                player1.sendTitle(ConfigEntry.GAME_POINT_TITLE1.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§c"), ConfigEntry.GAME_POINT_TITLE2.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§c"));
                player2.sendTitle(ConfigEntry.GAME_POINT_TITLE1.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§c"), ConfigEntry.GAME_POINT_TITLE2.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§c"));
            } else if (player == player2) {
                player1.sendTitle(ConfigEntry.GAME_POINT_TITLE1.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§9"), ConfigEntry.GAME_POINT_TITLE2.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§9"));
                player2.sendTitle(ConfigEntry.GAME_POINT_TITLE1.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§9"), ConfigEntry.GAME_POINT_TITLE2.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§9"));
            }
        } else win(player);
    }

    public void win(Player player) {

        resetBlocks();

        instance.getGameHandler().getGames().remove(this);
        instance.getGameHandler().getArenas().remove(this.arena);

        for (String uuid : getSpectator()) {
            Player player3 = Bukkit.getPlayer(UUID.fromString(uuid));
            player1.showPlayer(player3);
            player2.showPlayer(player3);
            player3.setAllowFlight(false);
            player3.setFlying(false);
            PlayerHandler playerHandler = new PlayerHandler(player3);
            playerHandler.changePlayerGameState(PlayerGameState.LOBBY);
            playerHandler.setScoreboard();
            playerHandler.setLobbyItems();
            playerHandler.teleportSpawn();
            if (player == player1) {
                player3.sendTitle(ConfigEntry.GAME_WIN_TITLE1.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§c").replaceAll("%WINORLOSE%", ""), ConfigEntry.GAME_WIN_TITLE2.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§c").replaceAll("%WINORLOSE%", ""));
            } else if (player == player2) {
                player3.sendTitle(ConfigEntry.GAME_WIN_TITLE1.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§9").replaceAll("%WINORLOSE%", ""), ConfigEntry.GAME_WIN_TITLE2.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§9").replaceAll("%WINORLOSE%", ""));
            }
        }

        PlayerHandler playerHandler1 = new PlayerHandler(player1);
        PlayerHandler playerHandler2 = new PlayerHandler(player2);

        playerHandler1.changePlayerGameState(PlayerGameState.LOBBY);
        playerHandler2.changePlayerGameState(PlayerGameState.LOBBY);

        playerHandler1.setLobbyItems();
        playerHandler2.setLobbyItems();

        playerHandler1.teleportSpawn();
        playerHandler2.teleportSpawn();

        if (player == player1) {
            playerHandler1.getStats().addWins(1);
            playerHandler2.getStats().addLoses(1);
            playerHandler1.getStats().addGames(1);
            playerHandler2.getStats().addGames(1);
            playerHandler1.getStats().addPoints(points.get(player1.getUniqueId().toString()));
            playerHandler2.getStats().addPoints(points.get(player2.getUniqueId().toString()));

            if (Bukkit.getOfflinePlayer(UUID.fromString(player1.getUniqueId().toString())).isOnline())
                player1.sendTitle(ConfigEntry.GAME_WIN_TITLE1.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§c").replaceAll("%WINORLOSE%", "Win"), ConfigEntry.GAME_WIN_TITLE2.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§c").replaceAll("%WINORLOSE%", "Win"));
            if (Bukkit.getOfflinePlayer(UUID.fromString(player2.getUniqueId().toString())).isOnline())
                player2.sendTitle(ConfigEntry.GAME_WIN_TITLE1.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§c").replaceAll("%WINORLOSE%", "Lose"), ConfigEntry.GAME_WIN_TITLE2.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§c").replaceAll("%WINORLOSE%", "Lose"));
        } else if (player == player2) {
            playerHandler2.getStats().addWins(1);
            playerHandler1.getStats().addLoses(1);
            playerHandler1.getStats().addGames(1);
            playerHandler2.getStats().addGames(1);
            playerHandler1.getStats().addPoints(points.get(player1.getUniqueId().toString()));
            playerHandler2.getStats().addPoints(points.get(player2.getUniqueId().toString()));


            if (Bukkit.getOfflinePlayer(UUID.fromString(player2.getUniqueId().toString())).isOnline())
                player2.sendTitle(ConfigEntry.GAME_WIN_TITLE1.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§9").replaceAll("%WINORLOSE%", "Win"), ConfigEntry.GAME_WIN_TITLE2.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§9").replaceAll("%WINORLOSE%", "Win"));
            if (Bukkit.getOfflinePlayer(UUID.fromString(player1.getUniqueId().toString())).isOnline())
                player1.sendTitle(ConfigEntry.GAME_WIN_TITLE1.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§9").replaceAll("%WINORLOSE%", "Lose"), ConfigEntry.GAME_WIN_TITLE2.getAsString().replaceAll("%PLAYER%", player.getDisplayName()).replaceAll("%TEAMCOLOR%", "§9").replaceAll("%WINORLOSE%", "Lose"));
        }


        playerHandler1.setScoreboard();
        playerHandler2.setScoreboard();

    }

    public int getID() {
        return ID;
    }

    public Game setID(int ID) {
        this.ID = ID;
        return this;
    }

    public Player getPlayer1() {
        return player1;
    }

    public List<Location> getBed_locs() {
        return bed_locs;
    }

    public Game setPlayer1(Player player1) {
        this.player1 = player1;
        return this;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Game setPlayer2(Player player2) {
        this.player2 = player2;
        return this;
    }

    public Arena getArena() {
        return arena;
    }

    public Game setArena(Arena arena) {
        this.arena = arena;
        return this;
    }

    public List<Block> getPlaced_blocks() {
        return placed_blocks;
    }

    public Map<String, Integer> getPoints() {
        return points;
    }

    public Game setPoints(Map<String, Integer> points) {
        this.points = points;
        return this;
    }
}
