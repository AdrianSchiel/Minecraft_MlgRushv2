package net.royalbyte.mlgrush.v2.game;

import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.database.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.UUID;

public class PlayerGameInv {


    Player player;
    String uuid;
    MySQL mySQL;

    public PlayerGameInv(Player player) {
        this.player = player;
        this.uuid = this.player.getUniqueId().toString();
        this.mySQL = MLGRush.getInstance().getMySQL();
    }

    public boolean inList() {
        ResultSet rs = this.mySQL.getResult("SELECT * FROM PlayerInvs WHERE UUID='" + this.uuid + "'");
        try {
            if (rs.next())
                return (rs.getString("UUID") != null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addPlayer(String name) {
        if (!inList())
            this.mySQL.update("INSERT INTO PlayerInvs (NAME , UUID , SLOT1, SLOT2,SLOT3,SLOT4,SLOT5,SLOT6,SLOT7,SLOT8,SLOT9) VALUES ('" + name + "', '" + this.uuid + "','0', '1', '2', '3', '4', '5', '6', '7', '8')");
    }

    public Integer getSlot(int id) {
        if (inList()) {
            ResultSet rs = this.mySQL.getResult("SELECT * FROM PlayerInvs WHERE UUID='" + this.uuid + "'");
            try {
                if (rs.next())
                    return Integer.valueOf(rs.getInt("SLOT" + id));
            } catch (SQLException sQLException) {
            }
        } else {
            addPlayer(Bukkit.getOfflinePlayer(UUID.fromString(this.uuid)).getName());
        }
        return Integer.valueOf(0);
    }

    public void setSlot(int id, int item) {
        if (inList()) {
            this.mySQL.update("UPDATE PlayerInvs SET SLOT" + id + "='" + item + "' WHERE UUID='" + this.uuid + "'");
        } else {
            addPlayer(Bukkit.getOfflinePlayer(UUID.fromString(this.uuid)).getName());
        }
    }

    public void openInventory(){
        Inventory inventory = Bukkit.createInventory(null, 9, "§7•§8● §bInventar §8●§7•");
        for (int i = 0; i < 9 ; i++) {
            ItemStack itemStack = MLGRush.getInstance().getDefaultGameInv().getHotbar().get(getSlot(i+1));
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setLore(Arrays.asList(new String[]{"§7ID: " + getSlot(i+1)}));
            itemStack.setItemMeta(itemMeta);
            inventory.setItem(i, itemStack);
        }
        this.player.openInventory(inventory);
    }

    public void setInventory(){
        for (int i = 0; i < 9 ; i++) {
            player.getInventory().setItem(i, MLGRush.getInstance().getDefaultGameInv().getHotbar().get(getSlot(i+1)));
        }
        player.getInventory().setChestplate(MLGRush.getInstance().getDefaultGameInv().getArmor().get("chestplate"));
        player.getInventory().setHelmet(MLGRush.getInstance().getDefaultGameInv().getArmor().get("helm"));
        player.getInventory().setLeggings(MLGRush.getInstance().getDefaultGameInv().getArmor().get("leggings"));
        player.getInventory().setBoots(MLGRush.getInstance().getDefaultGameInv().getArmor().get("boots"));
    }

}
