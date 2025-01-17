package com.tasktracker.cli.model.actions;

import com.tasktracker.cli.exception.ActionValidationException;

public class DeleteAction extends Action{
    int id;

    public DeleteAction(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public void validateInputs() throws ActionValidationException {
        if (id < 0) {
            throw new ActionValidationException("No task id provided.");
        }
    }
}
