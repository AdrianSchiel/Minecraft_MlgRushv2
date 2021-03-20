package net.royalbyte.mlgrush.v2.locations;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationHandler {

    private File file;
    private YamlConfiguration configuration;
    private Map<String, Locs> map;

    public LocationHandler() {
        this.file = new File("plugins//MLGRush//", "locs.yml");
        this.configuration = new YamlConfiguration().loadConfiguration(this.file);
        this.map = new HashMap<>();
        this.createFile();
        this.loadList();
    }

    private void loadList() {
        this.map.clear();
        for(String name: this.configuration.getStringList("list")){
            String dp = this.configuration.getString("locs." + name + ".dp");
            Location location = (Location) this.configuration.get("locs." + name + ".location");

            this.map.put(name,new Locs(name).setDp(dp).setLocation(location));
        }
    }

    private void createFile() {
        new File("plugins//MLGRush//").mkdirs();
        if(!this.file.exists()){
            try {
                this.file.createNewFile();
                List<String> list = new ArrayList<>();
                this.configuration.set("list", list);
                saveCFG();
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

    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfiguration() {
        return configuration;
    }

    public Map<String, Locs> getMap() {
        return map;
    }
}
