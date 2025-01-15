package com.tasktracker.cli.model;

public abstract class Action {
    public final void validate() {
        validateInputs();  // call subclass' implementation
    }

    protected abstract void validateInputs();
}