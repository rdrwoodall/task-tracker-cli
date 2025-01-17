package com.tasktracker.cli.models;

import java.util.List;

public class Store {
    private final List<Task> tasks;

    // copy constructor
    public Store(List<Task> nextTasks) {
        tasks = nextTasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}