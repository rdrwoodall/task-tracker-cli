package com.tasktracker.cli.model;

import com.tasktracker.cli.exception.ActionValidationException;

public abstract class Action {
    public final void validate() throws ActionValidationException {
        validateInputs();  // call subclass' implementation
    }

    protected abstract void validateInputs() throws ActionValidationException;
}