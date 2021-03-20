package net.royalbyte.mlgrush.v2.config;

import net.royalbyte.mlgrush.v2.MLGRush;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreboardConfig {

    File file;
    YamlConfiguration configuration;
    List<String> ingame, lobby;
    String displayname_ingame, displayname_lobby;
    MLGRush instance;

    public ScoreboardConfig() {
        this.file = new File("plugins//MLGRush//", "scroreboard.yml");
        this.configuration = new YamlConfiguration().loadConfiguration(this.file);
        this.ingame = new ArrayList<>();
        this.lobby = new ArrayList<>();
        this.instance = MLGRush.getInstance();

        createFile();
        loadScoreboards();
    }

    private void loadScoreboards() {
        this.ingame.clear();
        this.lobby.clear();
        this.displayname_ingame = this.configuration.getString("ingame_name");
        this.displayname_lobby = this.configuration.getString("lobby_name");

        for(String ingame : this.configuration.getStringList("ingame")){
            this.ingame.add(ingame);
        }
        for(String lobby : this.configuration.getStringList("lobby")){
            this.lobby.add(lobby);
        }

        Collections.reverse(lobby);
        Collections.reverse(ingame);
    }

    private void createFile() {

        new File("plugins//MLGRush//").mkdir();
        if (!file.exists()) {
            try {
                file.createNewFile();
                List<String> defaultlobby = new ArrayList<>();
                List<String> defaultIngame = new ArrayList<>();

                defaultlobby.add("&8&M&l------------" + "&1");
                defaultlobby.add("&7•&8● &7Wins" + "&2");
                defaultlobby.add("&8&l➜ &a %WINS% &3");
                defaultlobby.add("  " + "&4");
                defaultlobby.add("&7•&8● &7Looses" + "&5");
                defaultlobby.add("&8&l➜ &c %LOOSES% &6");
                defaultlobby.add("  " + "&7");
                defaultlobby.add("&7•&8● &7Dein Profil" + "&8");
                defaultlobby.add("&8&l➜&b %USERNAME% &9");
                defaultlobby.add(" " + "&a");
                defaultlobby.add("&7•&8● &7Online" + "&c");
                defaultlobby.add("&8&l➜ &e %ONLINEPLAYERS% &d");
                defaultlobby.add("&8&M&l------------" + "&b");

                defaultIngame.add("&8&M&l------------" + "&2");
                defaultIngame.add("&7•&8● &cTeam-Rot" + "&3");
                defaultIngame.add("&8&l➜ &7 %TEAMREDPOINTS% &4");
                defaultIngame.add(" " + "&5");
                defaultIngame.add("&7•&8● &9Team-Blau" + "&6");
                defaultIngame.add("&8&l➜ &7 %TEAMBLUEPOINTS% &7");
                defaultIngame.add(" " + "&8");
                defaultIngame.add("&7•&8● &7Arena" + "&9");
                defaultIngame.add("&8&l➜ &b %ARENANAME% &a");
                defaultIngame.add("&8&M&l------------" + "&c");
                
                this.configuration.set("lobby", defaultlobby);
                this.configuration.set("ingame", defaultIngame);
                this.configuration.set("lobby_name", MLGRush.getPrefix());
                this.configuration.set("ingame_name", MLGRush.getPrefix());

                saveCFG();
            } catch (IOException e) {
            }
        }
    }

    public List<String> getIngame() {
        return ingame;
    }

    public List<String> getLobby() {
        return lobby;
    }

    public String getDisplayname_ingame() {
        return displayname_ingame;
    }

    public String getDisplayname_lobby() {
        return displayname_lobby;
    }

    public void saveCFG() {
        try {
            this.configuration.save(this.file);
        } catch (IOException e) {
        }
    }
}
