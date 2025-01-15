package com.tasktracker.cli.model;

public class Add extends Action {
    private final String description;

    public Add(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    protected void validateInputs() {
        // TODO: needs to be implemented
        System.out.println("Add: validating inputs");
    }
}
