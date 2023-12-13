package com.example.taskmanagerv2;

public class Task {
    private static int nextId = 1;
    private String name;
    private String dueDate;
    private String priority;
    private int taskId;
    private String description;

    public Task(String description) {
        this.taskId = nextId++;
        this.description = description;
    }

    public Task(String name, String dueDate, String priority) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return name + "," + dueDate + "," + priority;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskName(String taskName) {
        this.name = taskName;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = name;
    }

    public String getDescription() {
        return description;
    }
}
