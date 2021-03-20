package net.royalbyte.mlgrush.v2.game;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.arena.Arena;
import net.royalbyte.mlgrush.v2.config.ConfigEntry;
import net.royalbyte.mlgrush.v2.itembuilder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class GameHandler {


    private List<Game> games;
    private List<Arena> arenas;
    MLGRush instance;

    public GameHandler() {
        this.instance = MLGRush.getInstance();
        this.games = new ArrayList<>();
        this.arenas = new ArrayList<>();
        startDeathzoneTimer();
    }

    public Game getGamefromPlayer(final Player player) {
        for (Game game : getGames()) {
            if (game.getPlayer1().equals(player) || game.getPlayer2().equals(player)) return game;
        }
        return null;
    }

    public void startDeathzoneTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (instance.getPlayerGameHandler().getPlayergamestates().get(player.getUniqueId().toString()).equals(PlayerGameState.INGAME)) {
                        Game game = getGamefromPlayer(player);
                        if (player.getLocation().getBlockY() <= game.getArena().getDeathzone()) {
                            new PlayerHandler(player).getPlayerInv().setInventory();
                            if (game.getPlayer1().equals(player))
                                player.teleport(game.getArena().getSpawn1());
                            else if (game.getPlayer2().equals(player))
                                player.teleport(game.getArena().getSpawn2());
                        }
                    }
                }
            }
        }.runTaskTimerAsynchronously(MLGRush.getInstance(), 10, 5);
    }

    public List<Arena> getArenas() {
        return arenas;
    }

    public Arena getNextFreeArena() {
        for (String name : instance.getArenaHandler().getMap().keySet()) {
            if (!arenas.contains(instance.getArenaHandler().getMap().get(name)))
                return instance.getArenaHandler().getMap().get(name);
        }
        return null;
    }

    public boolean existFreeArena() {
        return instance.getArenaHandler().getMap().size() > getArenas().size();
    }

    public void setQueneEntity() {
        Location location = instance.getLocationHandler().getMap().get("quene").getLocation();
        //DELETE OLD
        for (Entity entity : location.getWorld().getEntities()) {
            if (entity instanceof Skeleton) {
                if (entity.getLocation().equals(location)) {
                    ((Skeleton) entity).setHealth(0);
                    location.getWorld().getEntities().remove(entity);
                }
            }
        }

        //SET NEW
        Entity entity = location.getWorld().spawn(location, Skeleton.class);
        disableEntityAI(entity);
        entity.setCustomName(ConfigEntry.QUENE.getAsString());
        entity.setCustomNameVisible(true);
    }

    public void disableEntityAI(Entity entity) {
        net.minecraft.server.v1_8_R3.Entity nmsEnt = ((CraftEntity) entity).getHandle();
        NBTTagCompound tag = nmsEnt.getNBTTag();

        if (tag == null) {
            tag = new NBTTagCompound();
        }

        nmsEnt.c(tag);
        tag.setInt("NoAI", 1);
        nmsEnt.f(tag);
    }

    public List<Game> getGames() {
        return games;
    }

    public void openGameInv(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 3 * 9, "§7•§8● §bSpiele §8●§7•");
        int i = 0;
        for (Game game : games) {
            if (i <= 27) {
                inventory.setItem(i, new ItemBuilder(Material.STICK, 1).setName(game.getArena().getDisplayname().replaceAll("&", "§"))
                        .addLore("§7»").addLore("§7» §9" + game.getPlayer2().getName() + "§7(§b" + game.getPoints().get(game.getPlayer2().getUniqueId().toString()) + "§7)")
                        .addLore("§7» §c" + game.getPlayer1().getName() + "§7(§b" + game.getPoints().get(game.getPlayer1().getUniqueId().toString()) + "§7)")
                        .addLore("§7» Map: " + game.getArena().getName()).addLore("§7»").build());
                i++;
            }
        }
        player.openInventory(inventory);
    }
}
