package net.royalbyte.mlgrush.v2.commands;

import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.config.ConfigEntry;
import net.royalbyte.mlgrush.v2.locations.LocationHandler;
import net.royalbyte.mlgrush.v2.locations.Locs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_setLocs implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("MLGRush.setlocs")) {

                LocationHandler locationHandler = MLGRush.getInstance().getLocationHandler();
                if (args.length == 1) {
                   if(args[0].equalsIgnoreCase("spawn")) {
                       if (!locationHandler.getMap().containsKey("spawn")) {
                           Locs locs = new Locs("spawn").setDp("Spawn").setLocation(player.getLocation());
                           locs.save();
                           player.sendMessage("Die SpawnLocation wurde gesetzt");
                       } else player.sendMessage("Der Spawn wurde schon gesetzt, lösche die locs.yml");
                   }else if(args[0].equalsIgnoreCase("quene")) {
                       if (!locationHandler.getMap().containsKey("quene")) {
                           Locs locs = new Locs("quene").setDp("quene").setLocation(player.getLocation());
                           locs.save();
                           MLGRush.getInstance().getGameHandler().setQueneEntity();
                           player.sendMessage("Die warteschlangen-Location wurde gesetzt");
                       } else
                           player.sendMessage("Die Location Warteschlange wurde schon gesetzt, lösche die locs.yml");
                   }
                }
            } else player.sendMessage(ConfigEntry.NOPERM.getAsString());
        } else commandSender.sendMessage(ConfigEntry.MUST_A_PLAYER.getAsString());

        return true;
    }
}
