package com.tasktracker.cli.models.actions;

import com.tasktracker.cli.exceptions.ActionValidationException;

public class DeleteAction extends Action {
    int id;

    public DeleteAction(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void validateInputs() throws ActionValidationException {
        if (id < 0) {
            throw new ActionValidationException("No task id provided.");
        }
    }
}
