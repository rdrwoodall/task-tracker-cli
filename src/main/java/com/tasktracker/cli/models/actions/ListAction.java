package com.tasktracker.cli.models.actions;

import com.tasktracker.cli.exceptions.ActionValidationException;
import com.tasktracker.cli.models.Status;

public class ListAction extends Action {
    public static final String STATUS_STRING_DONE = "done";
    public static final String STATUS_STRING_TODO = "todo";
    public static final String STATUS_STRING_IN_PROGRESS = "in-progress";

    private Status status = null;
    private final String rawStatus;

    public ListAction(String rawStatus) {
        this.rawStatus = rawStatus;
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

        // handles setting the string status to the validated
        // enum type value for lookup later
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
