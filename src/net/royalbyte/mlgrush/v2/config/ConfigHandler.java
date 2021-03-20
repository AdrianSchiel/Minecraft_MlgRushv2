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
 *  ConfigHandler.java erstellt am 10.11.19, 14:28
 */

package net.royalbyte.mlgrush.v2.config;

import net.royalbyte.mlgrush.v2.logger.Logger;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;


public class ConfigHandler {

    private File configFile;
    private boolean configExisted;
    private YamlConfiguration configCfg;

    public ConfigHandler() {
        this.configFile = new File("plugins/MLGRush/config.yml");
        this.configExisted = configFile.exists();
        this.configCfg = YamlConfiguration.loadConfiguration(configFile);

        load();
    }

    /**
     * Main method to load the configs
     */
    public void load() {
        loadConfig(configCfg, configFile, ConfigEntry.values()[0], configExisted);
    }

    /**
     * Loads the ConfigType with the configs in the arguments
     */
    private void loadConfig(final YamlConfiguration cfg, final File file, final Enum<?> enu, final boolean existed) {
        for (final ConfigEntry entry : ConfigEntry.values())
            cfg.addDefault(entry.getTotalPath(), entry.getDefaultValue());


        cfg.options().copyDefaults(true);

        cfg.options().header(getConfigHeader());

        if (!existed) {
            save(file, cfg);
            return;
        }

        boolean save = false;
        for (final String key : cfg.getKeys(true)) {
            if (!key.contains("."))
                continue;

            try {
                ConfigEntry.getEntry(key).setValue(cfg.get(key), false);
            } catch (NullPointerException e) {
                cfg.set(key, null);
                save = true;
            }
        }

        if (save)
            save(file, cfg);
    }

    private void save(final File file, final YamlConfiguration cfg) {
        try {
            cfg.save(file);
        } catch (IOException e) {
            Logger.err("Failed saving file " + file.getName(), false);
        }
    }

    public YamlConfiguration getConfigCfg() {
        return configCfg;
    }

    public void saveConfig() {
        try {
            configCfg.save(configFile);
        } catch (IOException e) {
            Logger.err("Failed saving file " + configFile.getName(), false);
        }
    }

    /**
     * @return Every description of every ConfigEntry combined
     */
    private String getConfigHeader() {
        String header = "Config Einstellungen \r\n"
                + "WARNUNG: DIE RICHTIGE CONFIG BEFINDET SICH UNTEN, NICHT DIE '#' VOR DEN EINTRAEGEN WEGNEHMEN!\n Hier ist die Beschreibung der Config:";
        String desc = "";
        String oldCat = "";
        for (ConfigEntry entry : ConfigEntry.values()) {
            String category = entry.getSection().getRawPath();
            if (!oldCat.equals(""))
                if (!oldCat.equals(category))
                    desc = desc + "\n----------- " + category + " -----------";

            final String description = getArgsToString(entry.getDescription().split("\n"), "\n  ");
            oldCat = category;
            desc = desc + "\r\n" + " " + entry.getPath().replace(category + ".", "") + ":\n  " + description
                    + "\n  Default-Value: " + entry.getDefaultValue() + "\r\n";
        }
        return header + desc;
    }

    public static String getArgsToString(String[] args, String insertBewteen) {
        String command = "";
        for (String arg : args)
            if (command.equals(""))
                command = arg;
            else
                command = command + insertBewteen + arg;

        return command;
    }

}