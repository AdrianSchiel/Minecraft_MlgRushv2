/*
 *   _____ _          _____        _____        _  _
 *  / ____| |        |  __ \      |  __ \      | || |
 * | (___ | | ___   _| |__) |_   _| |__) |_   _| || |_
 *  \___ \| |/ / | | |  ___/\ \ / /  ___/\ \ / /__   _|
 *  ____) |   <| |_| | |     \ V /| |     \ V /   | |
 * |_____/|_|\_\\__, |_|      \_/ |_|      \_/    |_|
 *               __/ |
 *              |___/
 *
 *  Alle Rechte liegen bei dem Entwickler Adrian Schiel.
 *  Veränderungen an dem Plugin sind nur durch erlaubnis von Adrian Schiel erlaubt.
 *
 *  Copyright 2019 - 2020
 *
 *  LeatherBuilder.java erstellt am 10.11.19, 14:11
 */

package net.royalbyte.mlgrush.v2.itembuilder;


import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.List;

public class LeatherBuilder {

    private Material material;
    private String name;
    private List<String> lore;
    private Color color;
    private int count;

    public LeatherBuilder(Material material, String name) {
        this.material = material;
        this.name = name;
    }


    public void build(){
        ItemStack itemStack = new ItemStack(this.material, this.count);
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        leatherArmorMeta.setColor(this.color);
    }

    public LeatherBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public LeatherBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public LeatherBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    public LeatherBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public LeatherBuilder setCount(int count) {
        this.count = count;
        return this;
    }
}
