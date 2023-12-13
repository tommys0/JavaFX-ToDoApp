package com.example.taskmanagerv2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private String[] usernames = {"tommy", "admin", "denis", "patrik"};
    private String[] passwords = {"123", "admin", "123", "123"};

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String loggedInUser = usernameField.getText();
        String password = passwordField.getText();

        if (isValidLogin(loggedInUser, password)) {
            loadMainApplication(loggedInUser, event);
        } else {
            displayErrorMessage();
        }
    }

    @FXML
    private void handleClearButtonAction(ActionEvent event) {
        usernameField.clear(); // Clear the username field
        passwordField.clear(); // Clear the password field
        errorLabel.setVisible(false); // Hide the error label
    }

    private boolean isValidLogin(String username, String password) {
        // Check if the username exists in the array
        int index = indexOfUsername(username);
        if (index != -1) {
            // If the username exists, check the corresponding password at the same index
            return passwords[index].equals(password);
        }
        return false; // Username not found or incorrect password
    }

    private void displayErrorMessage() {
        errorLabel.setVisible(true); // Show the error message
        errorLabel.setText("Incorrect username or password. Please try again.");
    }

    private int indexOfUsername(String username) {
        // Method to find the index of the given username in the array
        for (int i = 0; i < usernames.length; i++) {
            if (usernames[i].equals(username)) {
                return i;
            }
        }
        return -1; // Username not found
    }


    // Modify loadMainApplication to accept the event parameter
    private void loadMainApplication(String loggedInUser, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainApp.fxml"));
            Parent root = loader.load();

            // Access the controller and set the logged-in user
            MainAppController mainController = loader.getController();
            mainController.setLoggedInUser(loggedInUser);

            // Show the main application window
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            // Close the login window upon successful login
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace(); // Handle potential exceptions accordingly
        }
    }


    private void loadAdminApplication(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AdminApp.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Task Manager - Admin");
            stage.setScene(scene);
            stage.show();

            // Close the login window upon successful login
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace(); // Handle potential exceptions accordingly
        }
    }

}
