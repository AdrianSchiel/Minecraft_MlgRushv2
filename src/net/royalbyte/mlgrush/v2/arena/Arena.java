package net.royalbyte.mlgrush.v2.arena;

import net.royalbyte.mlgrush.v2.MLGRush;
import org.bukkit.Location;

import java.util.List;

public class Arena {


    private String name, displayname;
    private int deathzone, maxhigh;
    private Location spawn1, spawn2, bed1_front, bed2_front, bed1_back, bed2_back;

    private MLGRush instance;

    public Arena(String name) {
        this.name = name;
        this.instance = MLGRush.getInstance();
    }


    public boolean save() {
        if (!exist()) {
            ArenaHandler arenaHandler = instance.getArenaHandler();
            arenaHandler.getConfiguration().set("arena." + name + ".name", name);
            arenaHandler.getConfiguration().set("arena." + name + ".dp", displayname);
            arenaHandler.getConfiguration().set("arena." + name + ".deathzone", deathzone);
            arenaHandler.getConfiguration().set("arena." + name + ".maxhigh", maxhigh);
            arenaHandler.getConfiguration().set("arena." + name + ".spawn1", spawn1);
            arenaHandler.getConfiguration().set("arena." + name + ".spawn2", spawn2);
            arenaHandler.getConfiguration().set("arena." + name + ".bed1front", bed1_front);
            arenaHandler.getConfiguration().set("arena." + name + ".bed2front", bed2_front);
            arenaHandler.getConfiguration().set("arena." + name + ".bed1back", bed1_back);
            arenaHandler.getConfiguration().set("arena." + name + ".bed2back", bed2_back);


            List<String> list = arenaHandler.getConfiguration().getStringList("list");
            list.add(this.name);
            arenaHandler.getConfiguration().set("list", list);

            arenaHandler.saveCFG();
            arenaHandler.getMap().put(this.name, this);


            return true;

        } else return false;
    }

    public boolean delete() {
        if (exist()){

            instance.getArenaHandler().getConfiguration().set("arena." +name, "");

            ArenaHandler arenaHandler = instance.getArenaHandler();
            List<String> list = arenaHandler.getConfiguration().getStringList("list");
            list.remove(this.name);
            arenaHandler.getConfiguration().set("list", list);

            instance.getArenaHandler().saveCFG();
            instance.getArenaHandler().getMap().remove(this.name);
            return true;
        }else return  false;
    }

    public Arena setDisplayname(String displayname) {
        this.displayname = displayname;
        return this;
    }

    public Location getSpawn1() {
        return spawn1;
    }

    public void setSpawn1(Location spawn1) {
        this.spawn1 = spawn1;
    }

    public Location getSpawn2() {
        return spawn2;
    }

    public void setSpawn2(Location spawn2) {
        this.spawn2 = spawn2;
    }

    public Arena setDeathzone(int deathzone) {
        this.deathzone = deathzone;
        return this;
    }

    public Arena setMaxhigh(int maxhigh) {
        this.maxhigh = maxhigh;
        return this;
    }

    public Arena setBed1_front(Location bed1_front) {
        this.bed1_front = bed1_front;
        return this;
    }

    public Arena setBed2_front(Location bed2_front) {
        this.bed2_front = bed2_front;
        return this;
    }

    public Arena setBed1_back(Location bed1_back) {
        this.bed1_back = bed1_back;
        return this;
    }

    public Arena setBed2_back(Location bed2_back) {
        this.bed2_back = bed2_back;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDisplayname() {
        return displayname;
    }

    public int getDeathzone() {
        return deathzone;
    }

    public int getMaxhigh() {
        return maxhigh;
    }

    public Location getBed1_front() {
        return bed1_front;
    }

    public Location getBed2_front() {
        return bed2_front;
    }

    public Location getBed1_back() {
        return bed1_back;
    }

    public Location getBed2_back() {
        return bed2_back;
    }

    public boolean exist() {
        return instance.getArenaHandler().getMap().containsKey(this.name);
    }
}
