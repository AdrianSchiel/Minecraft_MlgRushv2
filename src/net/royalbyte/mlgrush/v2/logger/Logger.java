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
 *  Logger.java erstellt am 10.11.19, 13:34
 */

package net.royalbyte.mlgrush.v2.logger;

import net.royalbyte.mlgrush.v2.MLGRush;
import org.bukkit.Bukkit;

public class Logger {

    public static void err(final String msg, final boolean bc){
        if(bc) Bukkit.broadcastMessage(MLGRush.getPrefix() + "§7§L[§4Error§7§l] §c" + msg);
        else Bukkit.getConsoleSender().sendMessage(MLGRush.getPrefix() + "§7§L[§4Error§7§l] §c" + msg);

    }

    public static void info(final String msg, final boolean bc){
        if(bc) Bukkit.broadcastMessage(MLGRush.getPrefix() + "§7§L[§eInfo§7§l] §4" + msg);
        else Bukkit.getConsoleSender().sendMessage(MLGRush.getPrefix() + "§7§L[§eInfo§7§l] §f" + msg);
    }

    public static void warning(final String msg, final boolean bc){
        if(bc) Bukkit.broadcastMessage(MLGRush.getPrefix() + "§7§L[§cWarning§7§l] §4" + msg);
        else Bukkit.getConsoleSender().sendMessage(MLGRush.getPrefix() + "§7§L[§cWarning§7§l] §4" + msg);
    }
    public static void success(final String msg, final boolean bc){
        if(bc) Bukkit.broadcastMessage(MLGRush.getPrefix() + "§7§L[§aErfolg§7§l] §2" + msg);
        else Bukkit.getConsoleSender().sendMessage(MLGRush.getPrefix() + "§7§L[§aErfolg§7§l] §2" + msg);
    }
}
