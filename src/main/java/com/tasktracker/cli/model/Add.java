package com.tasktracker.cli.model;

import com.tasktracker.cli.exception.ActionValidationException;

public class Add extends Action {
    private final String description;

    public Add(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    protected void validateInputs() throws ActionValidationException {
        // no empty or whitespace only values
        if (this.description.isBlank()) {
            throw new ActionValidationException("No task description provided.");
        }
    }
}
