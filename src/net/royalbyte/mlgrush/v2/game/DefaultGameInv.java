package net.royalbyte.mlgrush.v2.game;

import net.royalbyte.mlgrush.v2.itembuilder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultGameInv {

    File file;
    YamlConfiguration configuration;
    List<ItemStack> hotbar;
    Map<String, ItemStack> armor;

    public DefaultGameInv() {
        this.file = new File("plugins//MLGRush//", "kit.yml");
        new YamlConfiguration();
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
        this.hotbar = new ArrayList<>();
        this.armor = new HashMap<>();
        createFile();
        loadMaps();
    }

    public void loadMaps() {
        this.hotbar.clear();
        this.armor.clear();
        for (int i = 0; i < 9; i++)
            this.hotbar.add((ItemStack) this.configuration.get(String.valueOf(i + 1)));
        this.armor.put("helm", (ItemStack) this.configuration.get("helm"));
        this.armor.put("chestplate", (ItemStack) this.configuration.get("chestplate"));
        this.armor.put("leggings", (ItemStack) this.configuration.get("leggings"));
        this.armor.put("boots", (ItemStack) this.configuration.get("boots"));
    }

    private void createFile() {
        (new File("plugins//MLGRush//")).mkdir();
        try {
            if (!this.file.exists()) {
                this.file.createNewFile();
                ItemStack itemStack = (new ItemBuilder(Material.STICK, 1)).build();
                ItemMeta itemMeta = itemStack.getItemMeta();
                itemMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
                itemStack.setItemMeta(itemMeta);
                this.configuration.set("1", itemStack);
                ItemStack itemStack1 = (new ItemBuilder(Material.WOOD_PICKAXE, 1)).build();
                ItemMeta itemMeta1 = itemStack1.getItemMeta();
                itemMeta1.addEnchant(Enchantment.DIG_SPEED, 1, true);
                itemStack1.setItemMeta(itemMeta1);
                this.configuration.set("2", itemStack1);
                this.configuration.set("3", (new ItemBuilder(Material.SANDSTONE, 64)).build());
                this.configuration.set("4", (new ItemBuilder(Material.SANDSTONE, 64)).build());
                this.configuration.set("5", (new ItemBuilder(Material.SANDSTONE, 64)).build());
                this.configuration.set("6", (new ItemBuilder(Material.SANDSTONE, 64)).build());
                this.configuration.set("7", (new ItemBuilder(Material.SANDSTONE, 64)).build());
                this.configuration.set("8", (new ItemBuilder(Material.SANDSTONE, 64)).build());
                this.configuration.set("9", (new ItemBuilder(Material.SANDSTONE, 64)).build());
                this.configuration.set("helm", (new ItemBuilder(Material.STAINED_GLASS_PANE, 1)).build());
                this.configuration.set("chestplate", (new ItemBuilder(Material.STAINED_GLASS_PANE, 1)).build());
                this.configuration.set("leggings", (new ItemBuilder(Material.STAINED_GLASS_PANE, 1)).build());
                this.configuration.set("boots", (new ItemBuilder(Material.STAINED_GLASS_PANE, 1)).build());
                this.configuration.save(this.file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCFG(){
        try {
            this.configuration.save(this.file);
        } catch (IOException e) {
        }
    }

    public File getFile() {
        return this.file;
    }

    public YamlConfiguration getConfiguration() {
        return this.configuration;
    }

    public List<ItemStack> getHotbar() {
        return hotbar;
    }

    public Map<String, ItemStack> getArmor() {
        return this.armor;
    }

}
