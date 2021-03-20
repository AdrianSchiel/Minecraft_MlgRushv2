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
 *  ConfigSection.java erstellt am 10.11.19, 14:28
 */

package net.royalbyte.mlgrush.v2.config;

public enum ConfigSection {

    MESSAGES("messages"),
    LIZENZ("lizenz"),
    MYSQL("mysql"),
    SCOREBOARD("scoreboard"),
    OTHER("other");

    private String path;
    private ConfigSection(String path) {
        this.path = path;
    }

    public String getPath() {
        return path + ".";
    }

    public String getRawPath() {
        return path;
    }

    public static ConfigSection getSection(String path) {
        for (ConfigSection section : values()) {
            if (!section.getRawPath().equals(path))
                continue;

            return section;
        }

        return null;
    }
}
