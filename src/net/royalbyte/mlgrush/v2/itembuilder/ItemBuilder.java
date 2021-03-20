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
 *  ItemBuilder.java erstellt am 10.11.19, 14:11
 */

package net.royalbyte.mlgrush.v2.itembuilder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private Material material;
    private String name;
    private int count, subid;
    private List<String> lore;

    public ItemBuilder(final Material material, final int count) {
        this.material = material;
        this.count = count;
        this.lore = new ArrayList<>();
    }

    public ItemStack build(){
        ItemStack itemStack = new ItemStack(this.material, this.count, (short) this.subid);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(this.lore);
        itemMeta.setDisplayName(this.name);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public ItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder setCount(int count) {
        this.count = count;
        return this;
    }

    public ItemBuilder setSubid(int subid) {
        this.subid = subid;
        return this;
    }

    public ItemBuilder addLore(final String lore){
        this.lore.add(lore);
        return  this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }
}
