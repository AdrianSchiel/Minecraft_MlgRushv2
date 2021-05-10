package net.royalbyte.mlgrush.v2.database;


import net.royalbyte.mlgrush.v2.logger.Logger;

import java.sql.*;

public class MySQL {

    private String host, database, username, password;
    private int port;
    private Connection connection;

    public MySQL(String host, String database, String username, String password, int port) {
        this.host = host;
        this.database = database;
        this.username = username;
        this.password = password;
        this.port = port;

        this.connect();
        this.createTables();
    }

    private void createTables() {
        update("CREATE TABLE IF NOT EXISTS Stats (Name VARCHAR(16), UUID VARCHAR(100), points INT(100), wins INT(100) , loses INT(100), played_games INT(100));");
        update("CREATE TABLE IF NOT EXISTS PlayerInvs (NAME VARCHAR(16), UUID VARCHAR(100), SLOT1 INT(100), SLOT2 INT(100),SLOT3 INT(100),SLOT4 INT(100),SLOT5 INT(100),SLOT6 INT(100),SLOT7 INT(100),SLOT8 INT(100),SLOT9 INT(100))");
    }

    private void connect() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.username, this.password);
                Logger.success("§7Die §4MySQL-Datenbank §aerfolgreich §7wurde geladen.", false);
            } catch (SQLException e) {
                Logger.err("§7Fehler beim Laden der §4MySQL-Datenbank§7.", false);
                e.printStackTrace();
            }
        }
    }

    public void close() {
        if (isConnected()) {
            try {
                this.connection.close();
                Logger.success("§7Die §4MySQL-Datenbank §aerfolgreich§7 wurde geschlossen.", false);
            } catch (SQLException e) {
                Logger.err("§7Konnte die §4MySQL-Datenbank§7 nicht schließen.", false);
            }
        }
    }

    public void update(String update) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(update);
            statement.close();
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
    }

    public ResultSet getResult(String qry) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(qry);
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
        return resultSet;
    }


    public boolean isConnected() {
        return connection != null;
    }
}
