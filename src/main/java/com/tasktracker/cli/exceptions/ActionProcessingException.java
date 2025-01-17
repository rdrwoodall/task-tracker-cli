package com.tasktracker.cli.exceptions;

public class ActionProcessingException extends Exception {
    public ActionProcessingException(String message, Throwable err) {
        super(message, err);
    }
}
