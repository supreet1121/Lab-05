package org.example.comp288lab05;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.SQLException;
import java.util.List;

public class HelloController {
    @FXML
    private TextField playerIdField,firstNameField, lastNameField, emailField, phoneField, addressField, postalCodeField, provinceField, gameTitleField, gameGenreField;
    @FXML
    private ListView<String> playersListView;

    @FXML
    private TextField reportPlayerIdField;  // For the report input



    private DatabaseManager dbManager;

    @FXML
    public void initialize() {
        try {
            dbManager = new DatabaseManager();
        } catch (SQLException e) {
            showError("Database Connection Failed", e.getMessage());
        }
    }

    @FXML

    protected void addPlayer() {
        try {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();  // Add a TextField for address
            String postalCode = postalCodeField.getText();  // Add a TextField for postal code
            String province = provinceField.getText();  // Add a TextField for province
            dbManager.insertPlayer(firstName, lastName, email, phone, address, postalCode, province);
            showSuccess("Player Added Successfully");
        } catch (SQLException e) {
            showError("Failed to Add Player", e.getMessage());
        }
    }
    @FXML
    protected void updatePlayer() {
        try {
            int playerId = Integer.parseInt(playerIdField.getText());  // Add TextField for player ID
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            String postalCode = postalCodeField.getText();
            String province = provinceField.getText();
            dbManager.updatePlayer(playerId, firstName, lastName, email, phone, address, postalCode, province);
            showSuccess("Player Updated Successfully");
        } catch (SQLException e) {
            showError("Failed to Update Player", e.getMessage());
        }
    }


    @FXML
    protected void addGame() {
        try {
            String title = gameTitleField.getText();
            String genre = gameGenreField.getText();
            dbManager.insertGame(title, genre);
            showSuccess("Game Added Successfully");
        } catch (SQLException e) {
            showError("Failed to Add Game", e.getMessage());
        }
    }

    @FXML
    protected void viewPlayers() {
        try {
            playersListView.getItems().setAll(dbManager.getPlayers());
        } catch (SQLException e) {
            showError("Failed to Retrieve Players", e.getMessage());
        }
    }




    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
