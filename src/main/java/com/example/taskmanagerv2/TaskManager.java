package com.example.taskmanagerv2;

import java.util.*;

public class TaskManager {
    private static final Map<String, List<Task>> userTasks = new HashMap<>();

    public static void addTaskForUser(String username, Task task) {
        List<Task> tasks = userTasks.getOrDefault(username, new ArrayList<>());
        tasks.add(task);
        userTasks.put(username, tasks);
    }

    public static void removeTaskForUser(String username, Task taskToRemove) {
        List<Task> tasks = userTasks.getOrDefault(username, new ArrayList<>());
        tasks.remove(taskToRemove);
        userTasks.put(username, tasks);
    }

    public static List<Task> getTasksForUser(String username) {
        return userTasks.getOrDefault(username, new ArrayList<>());
    }
}


