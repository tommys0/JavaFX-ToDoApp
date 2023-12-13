package com.example.taskmanagerv2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root);

        // Set preferred width and height for the stage
        primaryStage.setWidth(600);
        primaryStage.setHeight(400);

        // Center the stage on the screen
        primaryStage.centerOnScreen();

        // Create a non-resizable window
        primaryStage.setResizable(false);

        // Remove window decorations for a borderless window
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // Set the scene to the stage
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

