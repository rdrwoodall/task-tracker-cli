package com.tasktracker.cli.model;

import com.tasktracker.cli.exception.ActionValidationException;

public class UpdateAction extends Action {
    int id;
    String description;

    public UpdateAction(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public void validateInputs() throws ActionValidationException {
        if (id < 0) {
            throw new ActionValidationException("No task id provided.");
        } else if (description.isBlank()) {
            throw new ActionValidationException("No task description provided.");
        }
    }
}
