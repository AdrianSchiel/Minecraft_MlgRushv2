package net.royalbyte.mlgrush.v2;

import net.royalbyte.mlgrush.v2.arena.ArenaHandler;
import net.royalbyte.mlgrush.v2.commands.Command_Arena;
import net.royalbyte.mlgrush.v2.commands.Command_build;
import net.royalbyte.mlgrush.v2.commands.Command_defaultItems;
import net.royalbyte.mlgrush.v2.commands.Command_setLocs;
import net.royalbyte.mlgrush.v2.config.ConfigEntry;
import net.royalbyte.mlgrush.v2.config.ConfigHandler;
import net.royalbyte.mlgrush.v2.config.ScoreboardConfig;
import net.royalbyte.mlgrush.v2.database.MySQL;
import net.royalbyte.mlgrush.v2.game.DefaultGameInv;
import net.royalbyte.mlgrush.v2.game.GameHandler;
import net.royalbyte.mlgrush.v2.game.PlayerGameHandler;
import net.royalbyte.mlgrush.v2.game.QueneHandler;
import net.royalbyte.mlgrush.v2.listener.*;
import net.royalbyte.mlgrush.v2.locations.LocationHandler;
import net.royalbyte.mlgrush.v2.locations.Locs;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class MLGRush extends JavaPlugin {


    ArenaHandler arenaHandler;
    PlayerGameHandler playerGameHandler;
    LocationHandler locationHandler;
    GameHandler gameHandler;
    QueneHandler queneHandler;
    ScoreboardConfig scoreboardConfig;
    ConfigHandler configHandler;
    DefaultGameInv defaultGameInv;
    MySQL mySQL;

    private static String prefix;
    private static MLGRush instance;

    @Override
    public void onEnable() {
        instance = this;

        this.configHandler = new ConfigHandler();
        prefix = (String) ConfigEntry.PREFIX.getValue();
        File file = new File("plugins/MLGRush/", "config.yml");
        YamlConfiguration cfg = new YamlConfiguration().loadConfiguration(file);


        this.mySQL = new MySQL(ConfigEntry.MYSQL_HOST.getAsString(), ConfigEntry.MYSQL_DATABASE.getAsString(), ConfigEntry.MYSQL_USERNAME.getAsString(), ConfigEntry.MYSQL_PASSWORD.getAsString(), ConfigEntry.MYSQL_PORT.getAsInteger());

        this.locationHandler = new LocationHandler();
        this.arenaHandler = new ArenaHandler();
        this.playerGameHandler = new PlayerGameHandler(this);
        this.gameHandler = new GameHandler();
        this.queneHandler = new QueneHandler();
        this.scoreboardConfig = new ScoreboardConfig();
        this.defaultGameInv = new DefaultGameInv();
        registerCommands();
        registerListener();
        if (!locationHandler.getMap().containsKey("spawn")) {
            Location location = new Location(Bukkit.getWorld("world"), 0, 100, 1000);
            Locs locs = new Locs("spawn").setDp("Spawn").setLocation(location);
            locs.save();
        }

    }

    @Override
    public void onDisable() {

    }

    private void registerCommands() {
        getCommand("arena").setExecutor(new Command_Arena());
        getCommand("setLocs").setExecutor(new Command_setLocs());
        getCommand("build").setExecutor(new Command_build());
        getCommand("defaultItems").setExecutor(new Command_defaultItems());
    }

    private void registerListener() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new Listener_Join(), getInstance());
        pluginManager.registerEvents(new Listener_Quit(), getInstance());
        pluginManager.registerEvents(new Listener_EntityDamage(), getInstance());
        pluginManager.registerEvents(new Listener_Quene(), getInstance());
        pluginManager.registerEvents(new Listener_Game(), getInstance());
        pluginManager.registerEvents(new Listener_PlayerGameInventory(), getInstance());
        pluginManager.registerEvents(new Listener_StatsItem(),  getInstance());
        pluginManager.registerEvents(new Listener_Lobby(),  getInstance());
        pluginManager.registerEvents(new Listener_Spectate(),  getInstance());
    }
    public LocationHandler getLocationHandler() {
        return locationHandler;
    }

    public static MLGRush getInstance() {
        return instance;
    }

    public PlayerGameHandler getPlayerGameHandler() {
        return playerGameHandler;
    }

    public ConfigHandler getConfigHandler() {
        return configHandler;
    }

    public static String getPrefix() {
        return prefix;
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    public QueneHandler getQueneHandler() {
        return queneHandler;
    }

    public ScoreboardConfig getScoreboardConfig() {
        return scoreboardConfig;
    }

    public ArenaHandler getArenaHandler() {
        return arenaHandler;
    }

    public DefaultGameInv getDefaultGameInv() {
        return defaultGameInv;
    }

    public MySQL getMySQL() {
        return mySQL;
    }
}
