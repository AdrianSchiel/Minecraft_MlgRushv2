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
 *  SkullBilder.java erstellt am 10.11.19, 14:11
 */

package net.royalbyte.mlgrush.v2.itembuilder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class SkullBuilder {

    private String name, owner;
    private int count;
    private List<String> lore;

    public SkullBuilder(String owner, int count) {
        this.owner = owner;
        this.count = count;
        this.lore = new ArrayList<>();
    }

    public ItemStack build(){
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, this.count, (byte) 3);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(this.owner);
        skullMeta.setDisplayName(this.name);
        skullMeta.setLore(this.lore);
        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

    public SkullBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SkullBuilder setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public SkullBuilder addLore(final String lore){
        this.lore.add(lore);
        return this;
    }

    public SkullBuilder setCount(int count) {
        this.count = count;
        return this;
    }

    public SkullBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }
}
