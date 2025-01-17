package com.tasktracker.cli.models;

import java.util.Calendar;

public class Task {
    int id = -1;
    String description;
    Status status;
    long createdAt;
    long updatedAt;

    // for add task case (id is unknown initially, will be set later via copy constructor)
    public Task(String description) {
        this.description = description;
        this.status = Status.TODO;
        long currentTime = Calendar.getInstance().getTimeInMillis();
        this.createdAt = currentTime;
        this.updatedAt = currentTime;
    }

    // for update task case
    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.status = Status.TODO;
        long currentTime = Calendar.getInstance().getTimeInMillis();
        this.createdAt = currentTime;
        this.updatedAt = currentTime;
    }

    // for delete task case
    public Task(int id) {
        this.id = id;
        this.description = "";
        this.status = Status.TODO;
        long currentTime = Calendar.getInstance().getTimeInMillis();
        this.createdAt = currentTime;
        this.updatedAt = currentTime;
    }

    // for the add task case after calculating new id
    // copy constructor (sort of, copy everything but id)
    public Task(int id, Task copy) {
        this.id = id;
        this.description = copy.description;
        this.status = copy.status;
        this.createdAt = copy.createdAt;
        this.updatedAt = copy.updatedAt;
    }

    // for the update task case
    // copy constructor (sort of, copy everything but id and createdAt)
    public Task(int id, long createdAt, Task copy) {
        this.id = id;
        this.description = copy.description;
        this.status = copy.status;
        this.createdAt = createdAt;
        this.updatedAt = copy.updatedAt;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() { return status; }

    public String getDescription() { return description; }

    public long getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return "\n\tid: " + id + "\n\tdescription: " + description + "\n\tstatus: " + status + "\n\tcreatedAt: " + createdAt + "\n\tupdatedAt: " + updatedAt;
    }
}
