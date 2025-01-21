package com.tasktracker.cli.models.actions;

import com.tasktracker.cli.exceptions.ActionValidationException;
import com.tasktracker.cli.models.Status;

public class MarkAction extends Action {
    int id;
    private final Status status;

    public MarkAction(int id, Status status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public void validateInputs() throws ActionValidationException {
        if (id < 0) {
            throw new ActionValidationException("No task id provided.");
        }
    }
}
