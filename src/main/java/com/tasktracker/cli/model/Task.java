package com.tasktracker.cli.model;

import java.util.Calendar;

public class Task {
    int id = -1;
    String description;
    Status status;
    long createdAt;
    long updatedAt;

    public Task(String description, Status status) {
        this.description = description;
        this.status = status;
        long currentTime = Calendar.getInstance().getTimeInMillis();
        this.createdAt = currentTime;
        this.updatedAt = currentTime;
    }

    // copy constructor (sort of, copy everything but id)
    public Task(int id, Task copy) {
        this.id = id;
        this.description = copy.description;
        this.status = copy.status;
        this.createdAt = copy.createdAt;
        this.updatedAt = copy.updatedAt;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() { return status; }

    public String toShortString() {
        return description;
    }

    @Override
    public String toString() {
        return "\n\tid: " + id + "\n\tdescription: " + description + "\n\tstatus: " + status + "\n\tcreatedAt: " + createdAt + "\n\tupdatedAt: " + updatedAt;
    }
}
