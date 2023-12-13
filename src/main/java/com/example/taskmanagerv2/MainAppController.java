package com.example.taskmanagerv2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainAppController {

    @FXML
    private TableView<Task> taskTableView;

    private String loggedInUser;
    private ObservableList<Task> tasks;
    @FXML
    private TableColumn<Task, Integer> taskIdColumn;

    @FXML
    private TableColumn<Task, String> descriptionColumn;


    public void setLoggedInUser(String username) {
        this.loggedInUser = username;
        System.out.println("Successful login as: " + loggedInUser);
        List<Task> taskList = TaskManager.getTasksForUser(loggedInUser);
        tasks = FXCollections.observableArrayList(taskList);
        taskTableView.setItems(tasks);
    }

    @FXML
    private void handleAddTask(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTask.fxml"));
        Parent root = loader.load();
        AddTaskController addTaskController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        addTaskController.setStage(stage); // Set the stage reference in AddTaskController

        stage.showAndWait(); // Show the stage

        // Get the task AFTER the stage has been closed
        Task newTask = addTaskController.getTask();

        if (newTask != null && loggedInUser != null) {
            // Assuming the task name acts as the description
            newTask.setDescription(newTask.getName()); // Set the description
            TaskManager.addTaskForUser(loggedInUser, newTask);
            tasks = FXCollections.observableArrayList(TaskManager.getTasksForUser(loggedInUser));
            taskTableView.setItems(tasks); // Update TableView with the new task list
        }
    }

    @FXML
    private void handleRemoveTask(ActionEvent event) {
        Task selectedTask = taskTableView.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            tasks.remove(selectedTask); // Remove from local list
            TaskManager.removeTaskForUser(loggedInUser, selectedTask); // Remove from TaskManager
        } else {
            System.out.println("No task selected.");
        }
    }

    public void initialize() {
        // Initialize columns (map columns to Task properties)
        taskIdColumn.setCellValueFactory(cellData -> {
            Task task = cellData.getValue();
            int rowIndex = taskTableView.getItems().indexOf(task);
            return new SimpleIntegerProperty(rowIndex + 1).asObject();
        });
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Additional setup for columns or table appearance can be done here

        // Set up selection behavior if needed
        taskTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
