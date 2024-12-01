package org.example.comp288lab05;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:oracle:thin:@199.212.26.208:1521:SQLD";
    private static final String DB_USERNAME = "COMP228_F24_soh_47";
    private static final String DB_PASSWORD = "kaurghuman1121";

    private Connection connection;

    public DatabaseManager() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    // Insert Player
    // Insert Player with additional fields (address, postal_code, province)
    public void insertPlayer(String firstName, String lastName, String email, String phone,
                             String address, String postalCode, String province) throws SQLException {
        String query = "INSERT INTO Supreet_Kaur_player (first_name, last_name, email, phone, address, postal_code, province) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, address);
            stmt.setString(6, postalCode);
            stmt.setString(7, province);
            stmt.executeUpdate();
        }
    }

    // Update Player Information
    public void updatePlayer(int playerId, String firstName, String lastName, String email,
                             String phone, String address, String postalCode, String province) throws SQLException {
        String query = "UPDATE Supreet_Kaur_player SET first_name = ?, last_name = ?, email = ?, phone = ?, address = ?, postal_code = ?, province = ? WHERE player_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.setString(5, address);
            stmt.setString(6, postalCode);
            stmt.setString(7, province);
            stmt.setInt(8, playerId);
            stmt.executeUpdate();
        }
    }



    // Insert Game
    public void insertGame(String title, String genre) throws SQLException {
        String query = "INSERT INTO Supreet_Kaur_game (title, genre) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, title);
            stmt.setString(2, genre);
            stmt.executeUpdate();
        }
    }

    // Assign Player to Game
    public void assignGameToPlayer(int playerId, int gameId) throws SQLException {
        String query = "INSERT INTO Supreet_Kaur_player_and_game (player_id, game_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, playerId);
            stmt.setInt(2, gameId);
            stmt.executeUpdate();
        }
    }

    // Retrieve Players
    public List<String> getPlayers() throws SQLException {
        List<String> players = new ArrayList<>();
        String query = "SELECT * FROM Supreet_Kaur_player";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                players.add(rs.getString("first_name") + " " + rs.getString("last_name"));
            }
        }
        return players;
    }


    // Close Connection
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
