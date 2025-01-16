package com.tasktracker.cli.model;

import com.tasktracker.cli.exception.ActionValidationException;

public class List extends Action {
    private static final String STATUS_STRING_DONE = "done";
    private static final String STATUS_STRING_TODO = "todo";
    private static final String STATUS_STRING_IN_PROGRESS = "in-progress";

    private Status status;
    private String rawStatus;

    public List(String rawStatus) {
        this.rawStatus = rawStatus;
        status = null;
    }

    public List(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    protected void validateInputs() throws ActionValidationException {
        // allow empty, white space only, and known values

        if (rawStatus.isBlank()) {
            return;
        }

        switch(rawStatus) {
            case STATUS_STRING_DONE:
                status = Status.DONE;
                return;
            case STATUS_STRING_TODO:
                status = Status.TODO;
                return;
            case STATUS_STRING_IN_PROGRESS:
                status = Status.IN_PROGRESS;
                return;
        }

        throw new ActionValidationException("Unknown list status '" + rawStatus + "' provided.");
    }
}
