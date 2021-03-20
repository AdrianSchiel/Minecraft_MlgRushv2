package net.royalbyte.mlgrush.v2.locations;

import net.royalbyte.mlgrush.v2.MLGRush;
import org.bukkit.Location;

import java.util.List;

public class Locs {

    private String name, dp;
    private Location location;
    private MLGRush instance;

    public Locs(final String name) {
        this.name = name;
        this.instance = MLGRush.getInstance();
    }


    public void save(){
        if (!exist()) {
            LocationHandler locationHandler = instance.getLocationHandler();
            locationHandler.getConfiguration().set("locs." + name + ".name", this.name);
            locationHandler.getConfiguration().set("locs." + name + ".dp", this.dp);
            locationHandler.getConfiguration().set("locs." + name + ".location", this.location);


            List<String> list = locationHandler.getConfiguration().getStringList("list");
            list.add(this.name);
            locationHandler.getConfiguration().set("list", list);

            locationHandler.saveCFG();
            locationHandler.getMap().put(this.name, this);

        }
    }

    public boolean exist() {
        return instance.getLocationHandler().getMap().containsKey(this.name);
    }

    public String getName() {
        return name;
    }

    public Locs setName(String name) {
        this.name = name;
        return this;
    }

    public String getDp() {
        return dp;
    }

    public Locs setDp(String dp) {
        this.dp = dp;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public Locs setLocation(Location location) {
        this.location = location;
        return this;
    }
}
