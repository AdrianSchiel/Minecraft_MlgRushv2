package net.royalbyte.mlgrush.v2.commands;

import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.config.ConfigEntry;
import net.royalbyte.mlgrush.v2.game.PlayerGameState;
import net.royalbyte.mlgrush.v2.game.PlayerHandler;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_build implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(player.hasPermission("MLGRush.build")){
                MLGRush instance = MLGRush.getInstance();
                if(instance.getPlayerGameHandler().getPlayergamestates().get(player.getUniqueId().toString()).equals(PlayerGameState.INGAME)){
                    player.sendMessage(ConfigEntry.NO_BUILD_WHILE_INGAME.getAsString());
                }else if(instance.getPlayerGameHandler().getPlayergamestates().get(player.getUniqueId().toString()).equals(PlayerGameState.BUILD)){
                    PlayerHandler playerHandler = new PlayerHandler(player);
                    playerHandler.changePlayerGameState(PlayerGameState.LOBBY);
                    playerHandler.setLobbyItems();
                    playerHandler.teleportSpawn();
                    player.sendMessage(ConfigEntry.PLAYER_BUILD_REMOVE.getAsString());
                }else{
                    PlayerHandler playerHandler = new PlayerHandler(player);
                    playerHandler.changePlayerGameState(PlayerGameState.BUILD);
                    player.setGameMode(GameMode.CREATIVE);
                    player.getInventory().clear();
                    player.getInventory().setArmorContents(null);
                    player.sendMessage(ConfigEntry.PLAYER_BUILD_ADD.getAsString());
                }
            }else player.sendMessage(ConfigEntry.NOPERM.getAsString());
        }else commandSender.sendMessage(ConfigEntry.MUST_A_PLAYER.getAsString());

        return true;
    }
}
