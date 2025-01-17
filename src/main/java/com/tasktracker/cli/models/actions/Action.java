package com.tasktracker.cli.models.actions;

import com.tasktracker.cli.exceptions.ActionValidationException;

public abstract class Action {
    public final void validate() throws ActionValidationException {
        validateInputs();  // call subclass' implementation
    }

    protected abstract void validateInputs() throws ActionValidationException;
}