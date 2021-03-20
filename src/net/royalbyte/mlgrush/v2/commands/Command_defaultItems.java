package net.royalbyte.mlgrush.v2.commands;

import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.config.ConfigEntry;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_defaultItems implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(player.hasPermission("MLGRush.defaultItems")){
                if(!(player.getItemInHand().getType() != null) || !player.getItemInHand().getType().equals(Material.AIR)) {
                    if(args.length == 1) {
                        if (args[0].equalsIgnoreCase("slot1")) {
                            MLGRush.getInstance().getDefaultGameInv().getConfiguration().set("1", player.getItemInHand());
                            MLGRush.getInstance().getDefaultGameInv().saveCFG();
                            MLGRush.getInstance().getDefaultGameInv().loadMaps();
                            player.sendMessage(ConfigEntry.SET_DEFAULT_ITEM.getAsString().replaceAll("%ID%", "1"));
                        } else if (args[0].equalsIgnoreCase("slot2")) {
                            MLGRush.getInstance().getDefaultGameInv().getConfiguration().set("2", player.getItemInHand());
                            MLGRush.getInstance().getDefaultGameInv().saveCFG();
                            MLGRush.getInstance().getDefaultGameInv().loadMaps();
                            player.sendMessage(ConfigEntry.SET_DEFAULT_ITEM.getAsString().replaceAll("%ID%", "2"));
                        } else if (args[0].equalsIgnoreCase("slot3")) {
                            MLGRush.getInstance().getDefaultGameInv().getConfiguration().set("3", player.getItemInHand());
                            MLGRush.getInstance().getDefaultGameInv().saveCFG();
                            MLGRush.getInstance().getDefaultGameInv().loadMaps();
                            player.sendMessage(ConfigEntry.SET_DEFAULT_ITEM.getAsString().replaceAll("%ID%", "3"));
                        } else if (args[0].equalsIgnoreCase("slot4")) {
                            MLGRush.getInstance().getDefaultGameInv().getConfiguration().set("4", player.getItemInHand());
                            MLGRush.getInstance().getDefaultGameInv().saveCFG();
                            MLGRush.getInstance().getDefaultGameInv().loadMaps();
                            player.sendMessage(ConfigEntry.SET_DEFAULT_ITEM.getAsString().replaceAll("%ID%", "4"));
                        } else if (args[0].equalsIgnoreCase("slot5")) {
                            MLGRush.getInstance().getDefaultGameInv().getConfiguration().set("5", player.getItemInHand());
                            MLGRush.getInstance().getDefaultGameInv().saveCFG();
                            MLGRush.getInstance().getDefaultGameInv().loadMaps();
                            player.sendMessage(ConfigEntry.SET_DEFAULT_ITEM.getAsString().replaceAll("%ID%", "5"));
                        } else if (args[0].equalsIgnoreCase("slot6")) {
                            MLGRush.getInstance().getDefaultGameInv().getConfiguration().set("6", player.getItemInHand());
                            MLGRush.getInstance().getDefaultGameInv().saveCFG();
                            MLGRush.getInstance().getDefaultGameInv().loadMaps();
                            player.sendMessage(ConfigEntry.SET_DEFAULT_ITEM.getAsString().replaceAll("%ID%", "6"));
                        } else if (args[0].equalsIgnoreCase("slot7")) {
                            MLGRush.getInstance().getDefaultGameInv().getConfiguration().set("7", player.getItemInHand());
                            MLGRush.getInstance().getDefaultGameInv().saveCFG();
                            MLGRush.getInstance().getDefaultGameInv().loadMaps();
                            player.sendMessage(ConfigEntry.SET_DEFAULT_ITEM.getAsString().replaceAll("%ID%", "7"));
                        } else if (args[0].equalsIgnoreCase("slot8")) {
                            MLGRush.getInstance().getDefaultGameInv().getConfiguration().set("8", player.getItemInHand());
                            MLGRush.getInstance().getDefaultGameInv().saveCFG();
                            MLGRush.getInstance().getDefaultGameInv().loadMaps();
                            player.sendMessage(ConfigEntry.SET_DEFAULT_ITEM.getAsString().replaceAll("%ID%", "8"));
                        } else if (args[0].equalsIgnoreCase("slot9")) {
                            MLGRush.getInstance().getDefaultGameInv().getConfiguration().set("9", player.getItemInHand());
                            MLGRush.getInstance().getDefaultGameInv().saveCFG();
                            MLGRush.getInstance().getDefaultGameInv().loadMaps();
                            player.sendMessage(ConfigEntry.SET_DEFAULT_ITEM.getAsString().replaceAll("%ID%", "9"));
                        } else if (args[0].equalsIgnoreCase("helm")) {
                            MLGRush.getInstance().getDefaultGameInv().getConfiguration().set("helm", player.getItemInHand());
                            MLGRush.getInstance().getDefaultGameInv().saveCFG();
                            MLGRush.getInstance().getDefaultGameInv().loadMaps();
                            player.sendMessage(ConfigEntry.SET_DEFAULT_ITEM.getAsString().replaceAll("%ID%", "Helm"));
                        } else if (args[0].equalsIgnoreCase("chestplate")) {
                            MLGRush.getInstance().getDefaultGameInv().getConfiguration().set("chestplate", player.getItemInHand());
                            MLGRush.getInstance().getDefaultGameInv().saveCFG();
                            MLGRush.getInstance().getDefaultGameInv().loadMaps();
                            player.sendMessage(ConfigEntry.SET_DEFAULT_ITEM.getAsString().replaceAll("%ID%", "Chestplate"));
                        } else if (args[0].equalsIgnoreCase("leggings")) {
                            MLGRush.getInstance().getDefaultGameInv().getConfiguration().set("leggings", player.getItemInHand());
                            MLGRush.getInstance().getDefaultGameInv().saveCFG();
                            MLGRush.getInstance().getDefaultGameInv().loadMaps();
                            player.sendMessage(ConfigEntry.SET_DEFAULT_ITEM.getAsString().replaceAll("%ID%", "Leggings"));
                        } else if (args[0].equalsIgnoreCase("boots")) {
                            MLGRush.getInstance().getDefaultGameInv().getConfiguration().set("boots", player.getItemInHand());
                            MLGRush.getInstance().getDefaultGameInv().saveCFG();
                            MLGRush.getInstance().getDefaultGameInv().loadMaps();
                            player.sendMessage(ConfigEntry.SET_DEFAULT_ITEM.getAsString().replaceAll("%ID%", "Boots"));
                        } else sendHelp(commandSender);
                    }else sendHelp(commandSender);
                }else player.sendMessage(ConfigEntry.HOLD_ITEM_IN_HAND.getAsString());
            }else player.sendMessage(ConfigEntry.NOPERM.getAsString());
        }else commandSender.sendMessage(ConfigEntry.MUST_A_PLAYER.getAsString());
        return true;
    }

    private void sendHelp(CommandSender commandSender) {
        commandSender.sendMessage(MLGRush.getPrefix().replaceAll("&", "§") + "§7/defaultItems <Slot[1-9]>");
        commandSender.sendMessage(MLGRush.getPrefix().replaceAll("&", "§") + "§7/defaultItems <helm>");
        commandSender.sendMessage(MLGRush.getPrefix().replaceAll("&", "§") + "§7/defaultItems <chestplate>");
        commandSender.sendMessage(MLGRush.getPrefix().replaceAll("&", "§") + "§7/defaultItems <leggings>");
        commandSender.sendMessage(MLGRush.getPrefix().replaceAll("&", "§") + "§7/defaultItems <boots>");
    }
}
