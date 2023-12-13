package com.example.taskmanagerv2;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTaskController {

    @FXML
    private TextField taskNameField;

    private Stage stage;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private TextField priorityField;

    public Task getTask() {
        String name = taskNameField.getText();
        String dueDate = dueDatePicker.getEditor().getText();
        String priority = priorityField.getText();

        stage.close();
        return new Task(name, dueDate, priority);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
