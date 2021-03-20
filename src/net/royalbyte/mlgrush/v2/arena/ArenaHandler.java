package net.royalbyte.mlgrush.v2.arena;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArenaHandler {

    public File file;
    public YamlConfiguration configuration;
    public Map<String, Arena> map;
    public Map<String, Arena> createmap;

    public ArenaHandler() {
        this.file = new File("plugins//MLGRush//", "arena.yml");
        this.configuration = new YamlConfiguration().loadConfiguration(this.file);
        this.map = new HashMap<>();
        this.createmap = new HashMap<>();
        this.createFile();
        this.loadMap();
    }

    private void loadMap() {
        this.map.clear();

        for (String name : configuration.getStringList("list")) {
            String dp = (String) getConfiguration().get("arena." + name + ".dp");
            int dz = (int) getConfiguration().get("arena." + name + ".deathzone");
            int mh = (int) getConfiguration().get("arena." + name + ".maxhigh");
            Location b1f = (Location) getConfiguration().get("arena." + name + ".bed1front");
            Location b2f = (Location) getConfiguration().get("arena." + name + ".bed2front");
            Location b1b = (Location) getConfiguration().get("arena." + name + ".bed1back");
            Location b2b = (Location) getConfiguration().get("arena." + name + ".bed2back");
            Location spawn1 = (Location) getConfiguration().get("arena." + name + ".spawn1");
            Location spawn2 = (Location) getConfiguration().get("arena." + name + ".spawn2");

            Arena arena = new Arena(name);
            arena.setBed1_back(b1b);
            arena.setBed1_front(b1f);
            arena.setBed2_back(b2b);
            arena.setBed2_front(b2f);
            arena.setDeathzone(dz);
            arena.setMaxhigh(mh);
            arena.setSpawn1(spawn1);
            arena.setSpawn2(spawn2);
            arena.setDisplayname(dp);

            this.map.put(name, arena);

        }

    }

    private void createFile() {
        new File("plugins//MLGRush").mkdirs();
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
                List<String> list = new ArrayList<>();
                this.configuration.set("list", list);
                this.configuration.save(this.file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveCFG() {
        try {
            this.configuration.save(this.file);
        } catch (IOException e) {


        }
    }

    public Map<String, Arena> getCreatemap() {
        return createmap;
    }

    public Map<String, Arena> getMap() {
        return map;
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfiguration() {
        return configuration;
    }
}
