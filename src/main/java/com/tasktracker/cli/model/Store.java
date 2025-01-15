package com.tasktracker.cli.model;

import java.util.List;

public class Store {
    private int count;
    private List<Task> tasks;

    // FIXME: do I need this or will it be implicitly created?
    public Store() {
        // nothing to do here
    }

    // copy constructor
    public Store(List<Task> nextTasks) {
        count = nextTasks.size();
        tasks = nextTasks;
    }

    public int getCount() {
        return count;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}