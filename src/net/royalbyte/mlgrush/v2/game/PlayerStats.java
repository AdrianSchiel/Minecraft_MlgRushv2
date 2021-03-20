package net.royalbyte.mlgrush.v2.game;

import net.royalbyte.mlgrush.v2.MLGRush;
import net.royalbyte.mlgrush.v2.database.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerStats {

    Player player;
    String uuid;
    MySQL mySQL;

    public PlayerStats(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId().toString();
        this.mySQL = MLGRush.getInstance().getMySQL();
    }

    public boolean inList() {
        ResultSet rs = mySQL.getResult("SELECT * FROM Stats WHERE UUID='" + uuid + "'");
        try {
            if (rs.next()) {
                return rs.getString("UUID") != null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addPlayer(String name) {
        if (!inList()) {
            mySQL.update("INSERT INTO Stats (Name , UUID , points, wins, loses, played_games) VALUES ('" + name
                    + "', '" + uuid + "','0','0', '0', '0')");
        }
    }

    /*
     * Games
     */

    public Integer getGames() {
        if (inList()) {
            ResultSet rs = mySQL.getResult("SELECT * FROM Stats WHERE UUID='" + uuid + "'");
            try {
                while (rs.next()) {
                    return rs.getInt("played_games");
                }
            } catch (SQLException e) {
            }
        } else {
            addPlayer(Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
        }
        return 0;
    }

    public void setGames(int games) {
        if (games < 0) {
            games = 0;
        }
        if (inList()) {
            mySQL.update("UPDATE Stats SET played_games='" + games + "' WHERE UUID='" + uuid + "'");
        } else {
            addPlayer(Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
        }
    }

    public void addGames(int games) {
        int i = getGames();
        i += games;
        mySQL.update("UPDATE Stats SET played_games='" + i + "' WHERE `UUID`='" + uuid + "'");
    }

    public void removeGames(int games) {
        int i = getGames();
        i -= games;
        mySQL.update("UPDATE Stats SET played_games='" + i + "' WHERE `UUID`='" + uuid + "'");
    }

    /*
     * Points
     */

    public Integer getPoints() {
        if (inList()) {
            ResultSet rs = mySQL.getResult("SELECT * FROM Stats WHERE UUID='" + uuid + "'");
            try {
                while (rs.next()) {
                    return rs.getInt("points");
                }
            } catch (SQLException e) {
            }
        } else {
            addPlayer(Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
        }
        return 0;
    }

    public void setPoints(int points) {
        if (points < 0) {
            points = 0;
        }
        if (inList()) {
            mySQL.update("UPDATE Stats SET points='" + points + "' WHERE UUID='" + uuid + "'");
        } else {
            addPlayer(Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
        }
    }

    public void addPoints(int points) {
        if (points < 0) {
            points = 0;
        }
        int i = getPoints();
        i += points;
        mySQL.update("UPDATE Stats SET points='" + i + "' WHERE `UUID`='" + uuid + "'");
    }

    public void removePoints(int points) {
        int i = getPoints();
        i -= points;
        mySQL.update("UPDATE Stats SET points='" + i + "' WHERE `UUID`='" + uuid + "'");
    }
    /*
     * Wins
     */

    public Integer getWins() {
        if (inList()) {
            ResultSet rs = mySQL.getResult("SELECT * FROM Stats WHERE UUID='" + uuid + "'");
            try {
                while (rs.next()) {
                    return rs.getInt("wins");
                }
            } catch (SQLException e) {
            }
        } else {
            addPlayer(Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
        }
        return 0;
    }

    public void setWins(int wins) {
        if (wins < 0) {
            wins = 0;
        }
        if (inList()) {
            mySQL.update("UPDATE Stats SET wins='" + wins + "' WHERE UUID='" + uuid + "'");
        } else {
            addPlayer(Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
        }
    }

    public void addWins(int wins) {
        int i = getWins();
        i += wins;
        mySQL.update("UPDATE Stats SET wins='" + i + "' WHERE `UUID`='" + uuid + "'");
    }

    public void removeWins(int wins) {
        int i = getWins();
        i -= wins;
        mySQL.update("UPDATE Stats SET wins='" + i + "' WHERE `UUID`='" + uuid + "'");
    }

    /*
     * Loses
     */

    public Integer getLoses() {
        if (inList()) {
            ResultSet rs = mySQL.getResult("SELECT * FROM Stats WHERE UUID='" + uuid + "'");
            try {
                while (rs.next()) {
                    return rs.getInt("loses");
                }
            } catch (SQLException e) {
            }
        } else {
            addPlayer(Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
        }
        return 0;
    }

    public void setLoses(int loses) {
        if (loses < 0) {
            loses = 0;
        }
        if (inList()) {
            mySQL.update("UPDATE Stats SET loses='" + loses + "' WHERE UUID='" + uuid + "'");
        } else {
            addPlayer(Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
        }
    }

    public void addLoses(int loses) {
        int i = getLoses();
        i += loses;
        mySQL.update("UPDATE Stats SET loses='" + i + "' WHERE `UUID`='" + uuid + "'");
    }

    public void removeLoses(int loses) {
        int i = getLoses();
        i -= loses;
        mySQL.update("UPDATE Stats SET loses='" + i + "' WHERE `UUID`='" + uuid + "'");
    }

    /*
     * KD
     */

    public double getKD() {

        double looses = getLoses();
        double wins = getWins();
        double KD = (wins / looses);
        KD = (Math.round(KD * 100.0) / 100.0);

        return KD;

    }

}
