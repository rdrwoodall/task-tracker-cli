package com.tasktracker.cli.model;

import java.util.Calendar;

public class Task {
    int id = -1;
    String description = "";
    Status status = Status.TODO;
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
        return this.id;
    }

    public String toShortString() {
        return this.description;
    }

    @Override
    public String toString() {
        return "\n\tid: " + this.id + "\n\tdescription: " + this.description + "\n\tstatus: " + this.status + "\n\tcreatedAt: " + this.createdAt + "\n\tupdatedAt: " + this.updatedAt;
    }
}
