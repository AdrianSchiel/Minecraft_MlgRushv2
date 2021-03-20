package net.royalbyte.mlgrush.v2.commands;

import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.arena.Arena;
import net.royalbyte.mlgrush.v2.arena.ArenaHandler;
import net.royalbyte.mlgrush.v2.config.ConfigEntry;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Arena implements CommandExecutor {

    //Arena create Name
    //arena set <location...>
    //arena finish
    //arena delete Name

    ArenaHandler arenaHandler = MLGRush.getInstance().getArenaHandler();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
    if(commandSender.hasPermission("MLGRush.arena")) {
        if (args[0].equalsIgnoreCase("create")) {
            if (args.length == 2) {
                if (commandSender instanceof Player) {
                    Player player = (Player) commandSender;
                    if (!arenaHandler.getCreatemap().containsKey(player.getUniqueId().toString())) {
                        Arena arena = new Arena(args[1]);
                        arenaHandler.getCreatemap().put(player.getUniqueId().toString(), arena);
                        player.sendMessage(MLGRush.getPrefix() + "§7Setze den Anzeigenamen mit §c/arena set Displayname <Name>");
                    } else player.sendMessage(MLGRush.getPrefix() + "§cDu erstellst schon eine Map");
                } else commandSender.sendMessage(ConfigEntry.MUST_A_PLAYER.getAsString());
            } else commandSender.sendMessage(MLGRush.getPrefix() + "§c/arena help");
        } else if (args[0].equalsIgnoreCase("delete")) {
            if (args.length == 2) {
                if (commandSender instanceof Player) {
                    Player player = (Player) commandSender;
                    Arena arena = new Arena(args[1]);
                    if (arena.exist()) {
                        arena.delete();
                        player.sendMessage(MLGRush.getPrefix() +  "§7Die Arena §c" + args[1] + "§7 wurde gelöscht.");
                    } else player.sendMessage(MLGRush.getPrefix() + "§cDie Map gibt es nicht.");
                } else commandSender.sendMessage(ConfigEntry.MUST_A_PLAYER.getAsString());
            } else commandSender.sendMessage(MLGRush.getPrefix() + "§c/arena help");
        } else if (args[0].equalsIgnoreCase("set")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                if (arenaHandler.getCreatemap().containsKey(player.getUniqueId().toString())) {
                    Arena arena = arenaHandler.getCreatemap().get(player.getUniqueId().toString());
                    switch (args[1].toLowerCase()) {
                        case "displayname":
                            String dp = "";
                            for (int i = 2; i != args.length; i++) {
                                dp += " " + args[i];
                            }
                            arena.setDisplayname(dp);
                            arenaHandler.getCreatemap().put(player.getUniqueId().toString(), arena);
                            player.sendMessage(MLGRush.getPrefix() + "§7Setze nun deathzone mit §b/arena set deathzone");
                            break;
                        case "deathzone":
                            int y = player.getLocation().getBlockY();
                            arena.setDeathzone(y);
                            arenaHandler.getCreatemap().put(player.getUniqueId().toString(), arena);
                            player.sendMessage(MLGRush.getPrefix() + "§7Setze nun die Maximale bauhöhe mit §b/arena set maxhigh");
                            break;
                        case "maxhigh":
                            int y2 = player.getLocation().getBlockY();
                            arena.setMaxhigh(y2);
                            arenaHandler.getCreatemap().put(player.getUniqueId().toString(), arena);
                            player.sendMessage(MLGRush.getPrefix() + "§7Setze die Vorderseite des Bettes von Team Rot mit §b/arena set bed1_front");
                            break;
                        case "bed1_front":
                            Location bed1front = player.getLocation();
                            arena.setBed1_front(bed1front);
                            arenaHandler.getCreatemap().put(player.getUniqueId().toString(), arena);
                            player.sendMessage(MLGRush.getPrefix() + "§7Setze die Hinterseite des Bettes von Team Rot mit §b/arena set bed1_back");
                            break;
                        case "bed1_back":
                            Location bed1back = player.getLocation();
                            arena.setBed1_back(bed1back);
                            arenaHandler.getCreatemap().put(player.getUniqueId().toString(), arena);
                            player.sendMessage(MLGRush.getPrefix() + "§7Setze die Vorderseite des Bettes von Team Blau mit §b/arena set bed2_front");
                            break;
                        case "bed2_front":
                            Location bed2front = player.getLocation();
                            arena.setBed2_front(bed2front);
                            arenaHandler.getCreatemap().put(player.getUniqueId().toString(), arena);
                            player.sendMessage(MLGRush.getPrefix() + "§7Setze die Hinterseite des Bettes von Team Rot mit §b/arena set bed2_back");
                            break;
                        case "bed2_back":
                            Location bed2back = player.getLocation();
                            arena.setBed2_back(bed2back);
                            arenaHandler.getCreatemap().put(player.getUniqueId().toString(), arena);
                            player.sendMessage(MLGRush.getPrefix() + "§7Setze den Spawnpunkt von Team Rot mit §b/arena set spawn1");
                            break;
                        case "spawn1":
                            Location spawn1 = player.getLocation();
                            arena.setSpawn1(spawn1);
                            arenaHandler.getCreatemap().put(player.getUniqueId().toString(), arena);
                            player.sendMessage(MLGRush.getPrefix() + "§7Setze den Spawnpunkt von Team Blau mit §b/arena set spawn2");
                            break;
                        case "spawn2":
                            Location spawn2 = player.getLocation();
                            arena.setSpawn2(spawn2);
                            arenaHandler.getCreatemap().put(player.getUniqueId().toString(), arena);
                            player.sendMessage(MLGRush.getPrefix() + "§7Beende die Einrichtung mit §b/arena finish");
                            break;
                    }

                } else player.sendMessage(MLGRush.getPrefix() + "§cDu erstellst keine Arena.");
            } else commandSender.sendMessage(ConfigEntry.MUST_A_PLAYER.getAsString());
        } else if (args[0].equalsIgnoreCase("finish")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                if (arenaHandler.getCreatemap().containsKey(player.getUniqueId().toString())) {
                    Arena arena = arenaHandler.getCreatemap().get(player.getUniqueId().toString());
                    arena.save();
                    arenaHandler.getCreatemap().remove(player.getUniqueId().toString());
                    player.sendMessage(MLGRush.getPrefix() + arena.getDisplayname().replaceAll("&", "§") + "§7 wurde fertiggestellt.");
                } else player.sendMessage(MLGRush.getPrefix() + "§cDu erstellst keine Arena.");
            } else commandSender.sendMessage(ConfigEntry.MUST_A_PLAYER.getAsString());
        }else if(args[0].equalsIgnoreCase("help")){
            commandSender.sendMessage("§7/arena create <Name>");
            commandSender.sendMessage("§7/arena delete <Name>");
            commandSender.sendMessage("§7/arena finish <Name>");
        }else commandSender.sendMessage(MLGRush.getPrefix() + "§c/arena help");

    }else commandSender.sendMessage(ConfigEntry.NOPERM.getAsString());

        return true;
    }
}
