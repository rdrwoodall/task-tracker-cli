package com.tasktracker.cli.exception;

public class ActionProcessingException extends Exception {
    public ActionProcessingException(String message, Throwable err) {
        super(message, err);
    }
}
