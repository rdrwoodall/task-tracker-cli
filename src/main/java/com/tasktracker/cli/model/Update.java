package com.tasktracker.cli.model;

public class Update extends Action {
    int id;
    String status;

    public Update(int id, String status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public void validateInputs() {
        System.out.println("Update: validating inputs");
    }
}
