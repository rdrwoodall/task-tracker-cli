package com.tasktracker.cli.model;

import com.tasktracker.cli.exception.ActionValidationException;

public class AddAction extends Action {
    private final String description;

    public AddAction(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    protected void validateInputs() throws ActionValidationException {
        // no empty or whitespace only values
        if (description.isBlank()) {
            throw new ActionValidationException("No task description provided.");
        }
    }
}
