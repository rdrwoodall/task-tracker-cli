package com.tasktracker.cli.exceptions;

public class TaskStoreException extends Exception {
    public TaskStoreException(String errorMessage) { super(errorMessage); }

    public TaskStoreException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}