package net.royalbyte.mlgrush.v2.config;

import net.royalbyte.mlgrush.v2.MLGRush;

public enum ConfigEntry {


    LIZENZ(ConfigSection.LIZENZ, "lizenz", "YOUR_LIZENZ", "LIZENZ von der bekommenen Bestellung."),
    EMAIL(ConfigSection.LIZENZ, "shop_email", "YOUR_SHOP_EMAIL", "Email mit dem Ihr Account im Shop registriert ist."),
    PREFIX(ConfigSection.MESSAGES, "prefix", "&7[&bMLGRush&7]", "Prefix von jeder Textausgabe."),
    MUST_A_PLAYER(ConfigSection.MESSAGES, "must_a_player", "%PREFIX% &cDu musst ein Spieler sein.", "Nachricht in der Console falls ein Spieler benötigt wird."),
    NOPERM(ConfigSection.MESSAGES, "noperm", "%PREFIX% &cDir fehlt das Recht hierfür.", "Nachricht falls eine Permission fehlt."),
    MYSQL_HOST(ConfigSection.MYSQL, "host", "localhost", "MYSQl Host"),
    MYSQL_USERNAME(ConfigSection.MYSQL, "username", "root", "MYSQl USERNAME"),
    MYSQL_PORT(ConfigSection.MYSQL, "port", 3306, "MYSQl Port"),
    MYSQL_DATABASE(ConfigSection.MYSQL, "database", "MLGRush", "MYSQl Database"),
    MYSQL_PASSWORD(ConfigSection.MYSQL, "password", "", "MYSQl Password"),
    PLAYER_BUILD_ADD(ConfigSection.MESSAGES, "noperm", "%PREFIX% &7Du bist nun im &bBuild-Modus&7.", "Nachricht an den Spieler wenn er in den Build-Modus geht."),
    PLAYER_BUILD_REMOVE(ConfigSection.MESSAGES, "noperm", "%PREFIX% &7Du bist nun nicht mehr im &bBuild-Modus&7.", "Nachricht an den Spieler wenn er den Build-Modus verlässt"),
    PLAYER_LEAVE_QUENE(ConfigSection.MESSAGES, "player_leave_quene", "%PREFIX% &7Du hast die &bWarteschlange &cverlassen&7.", "Nachricht für den Spieler wenn er die Warteschlange verlässt."),
    PLAYER_JOIN_QUENE(ConfigSection.MESSAGES, "player_join_quene", "%PREFIX% &7Du hast die &bWarteschlange &abetreten&7.", "Nachricht für den Spieler wenn er in die Warteschlange joint."),
    PLAYER_CHALLENGE_PLAYER1(ConfigSection.MESSAGES, "player_challenge_player1", "%PREFIX% &7Du hast&b %PLAYER% &7herausgefordert&7.", "Nachricht für den Spieler wenn er jemanden herausgefordert hat."),
    PLAYER_CHALLENGE_PLAYER2(ConfigSection.MESSAGES, "player_challenge_player2", "%PREFIX% &7Du wurdest von&b %PLAYER% &7herausgefordert&7.", "Nachricht für den Spieler wenn er herausgefordert wurde."),
    PLAYER_REMOVECHALLENGE(ConfigSection.MESSAGES, "player_remove_challenge", "%PREFIX% &7Du hast deine &bHerausforderung &7zurückgezogen.", "Nachricht für den Spieler wenn er seien Herausforderung zurück zieht."),
    PLAYER_QUENE_NO_ARENA_FOUND(ConfigSection.MESSAGES, "player_quene_no_arena_found", "%PREFIX% &7Leider ist aktuell keine &bArena &7frei.", "Nachricht falls keine Arena frei ist."),
    GAME_START_TITLE1(ConfigSection.MESSAGES, "game_start_title1", "&7Du bist in Team", "Oberer Teil des Titles wenn ein Game startet."),
    GAME_START_TITLE2(ConfigSection.MESSAGES, "game_start_title2", "%TEAM%", "Unterer Teil des Titles wenn ein Game startet."),
    GAME_POINT_TITLE1(ConfigSection.MESSAGES, "game_point_title1", "%TEAMCOLOR% %PLAYER% &7hat eine Runde gewonnen", "Oberer Teil des Titles wenn ein Punkt geholt wird."),
    GAME_WIN_TITLE1(ConfigSection.MESSAGES, "game_win_title1", "%TEAMCOLOR% %PLAYER% &7hat &agewonnen", "Oberer Teil des Titles wenn eine Runde gewonnen wird."),
    GAME_POINT_TITLE2(ConfigSection.MESSAGES, "game_point_title2", "&a+1 Punkt", "Unterer Teil des Titles wenn ein Punkt geholt wird."),
    GAME_WIN_TITLE2(ConfigSection.MESSAGES, "game_win_title2", "&a+1 %WINORLOSE%", "Unterer Teil des Titles wenn eine Runde gewonnen wird."),
    GAME_WIN_POINTS(ConfigSection.OTHER, "game_win_points", 5, "Anzahl an Punkten die ein Spieler braucht um zu gewinnen."),
    NO_BUILD_WHILE_INGAME(ConfigSection.MESSAGES, "no_build_while_ingame", "%PREFIX% &cDu kannst nicht in den Buildmodus während einer Runde.", "Nachricht an den Spieler wenn er während einer Runde in den Build-Modus möchte."),
    PLAYER_JOIN(ConfigSection.MESSAGES, "player_join", "%PREFIX% &7Der Spieler&b %PLAYER% &7hat den Server &abetreten&7.", "Nachricht wenn ein Spieler join."),
    PLAYER_QUIT(ConfigSection.MESSAGES, "player_quit", "%PREFIX% &7Der Spieler&b %PLAYER% &7hat den Server &cverlassen&7.", "Nachricht wenn ein Spieler leavt."),
    PLAYER_INV_SAVE(ConfigSection.MESSAGES, "inv_save", "%PREFIX% &7Du hast dein Inventar gespeichert.", "Nachricht wenn ein Spieler sein Inventar speichert."),
    GAME_OWNBED(ConfigSection.MESSAGES, "game_own_bed", "%PREFIX% &cDu kannst dein eigenes Bett nicht abbauen.", "Nachricht wenn ein Spieler sein eigenes Bett abbauen will."),
    SPECTATE_GAMENOTFOUND(ConfigSection.MESSAGES, "spectate_game_not_found", "%PREFIX% &cDiese Runde wurde nicht gefunden.", "Nachricht wenn eine Runde nicht gefunden wurde."),
    SPECTATE_NOTIFY(ConfigSection.MESSAGES, "spectate_notify", "%PREFIX% &7Du schaust dir nun das Game&b %ID% &7an.", "Nachricht wenn ein Spieler sich eine Runde anschaut."),
    SPECTATE_LEAVE(ConfigSection.MESSAGES, "spectate_leave", "%PREFIX% &7Du hast das Game&b %ID% &7verlassen.", "Nachricht wenn ein Spieler die Runde verlässt."),
    HOLD_ITEM_IN_HAND(ConfigSection.MESSAGES, "hold_item_in_hand", "%PREFIX% &cDu musst ein Item in der Hand halten.", "Nachricht wenn ein Spieler kein Item in der Hand hat."),
    SET_DEFAULT_ITEM(ConfigSection.MESSAGES, "set_default_item", "%PREFIX% &7Du hast das Standart-Item[&b %ID% &7] gesetzt.", "Nachricht wenn ein Spieler ein Standart-Item gesetzt gat."),
    QUENE(ConfigSection.OTHER, "warteschlange.displayname", "&7[&bWarteschlange&7]", "Displayname von der Warteschlange.");


    private ConfigSection section;
    private String path;
    private String description;
    private Object value;
    private Object defaultValue;

    private ConfigEntry(ConfigSection section, String path, Object value, String description) {
        this.section = section;
        this.path = path;
        this.value = value;
        this.defaultValue = value;
        this.description = description;
    }

    public ConfigSection getSection() {
        return section;
    }

    public String getPath() {
        return path;
    }

    public String getAsString() {
        return String.valueOf(value).replaceAll("%PREFIX%", String.valueOf(ConfigEntry.PREFIX.getValue())).replaceAll("&", "§");
    }

    public boolean is() {
        return (boolean) value;
    }

    public Integer getAsInteger() {
        return (int) value;
    }

    public Object getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public void setValue(Object value, boolean save) {
        this.value = value;

        if (save) {
            MLGRush.getInstance().getConfigHandler().getConfigCfg().set(getTotalPath(), value);
            MLGRush.getInstance().getConfigHandler().saveConfig();
        }
    }

    public String getTotalPath() {
        return section.getPath() + path;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public static ConfigEntry getEntry(String path) {
        for (ConfigEntry entry : values()) {
            if (!entry.getTotalPath().equals(path))
                continue;

            return entry;
        }

        return null;
    }
}
