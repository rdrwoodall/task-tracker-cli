package com.tasktracker.cli.exception;

public class TaskStoreException extends Exception {
    public TaskStoreException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}