package com.tasktracker.cli;

import com.tasktracker.cli.exceptions.ActionProcessingException;
import com.tasktracker.cli.exceptions.ActionValidationException;
import com.tasktracker.cli.models.*;
import com.tasktracker.cli.models.actions.*;

import java.util.List;

/**
 * ActionProcessor is responsible for generating an Action and Task based on the users input.
 * Once the Action is generated/validated, the Task is passed to the TaskStore for further processing
 * (i.e. write/read).
 * */
public final class ActionProcessor {
    TaskStore store;

    public ActionProcessor() {
        this.store = new TaskStore();
    }

    public void add(String taskStatus) throws ActionProcessingException {
        AddAction addAction = new AddAction(taskStatus);

        try {
            addAction.validate();
            Task task = new Task(addAction.getDescription());
            this.store.addTask(task);
        }
        catch (ActionValidationException e) {
            throw new ActionProcessingException(e.getMessage(), e);
        }
        catch (Exception e) {
            throw new ActionProcessingException(e.getMessage(), e.getCause());
        }
    }

    public void list(String rawStatus) throws ActionProcessingException {
        ListAction listAction = new ListAction(rawStatus);
        List<Task> tasks;

        try {
            listAction.validate();
            tasks = this.store.getTasks(listAction);
        }
        catch (ActionValidationException e) {
            throw new ActionProcessingException(e.getMessage(), e);
        }
        catch (Exception e) {
            throw new ActionProcessingException(e.getMessage(), e.getCause());
        }

        if (tasks.isEmpty()) {
            System.out.println("List: no tasks found");
            return;
        }

        System.out.println("List: retrieved " + tasks.size() + " task(s)");
        System.out.println("\tID\t|\tSTATUS\t|\tTASK");
        System.out.println("--------------------------------------------------");
        for (Task t : tasks) {
            System.out.println("\t" + t.getId() + "\t|\t" + t.getStatus() + "\t|\t" + t.getDescription());
        }
    }

    public void update(int id, String status) throws ActionProcessingException {
        UpdateAction updateAction = new UpdateAction(id, status);

        try {
            updateAction.validateInputs();
            Task task = new Task(updateAction.getId(), updateAction.getDescription());
            this.store.updateTask(task);
        }
        catch (ActionValidationException e) {
            throw new ActionProcessingException(e.getMessage(), e);
        }
        catch (Exception e) {
            throw new ActionProcessingException(e.getMessage(), e.getCause());
        }


    }

    public void delete(int id) throws ActionProcessingException {
        DeleteAction deleteActionAction = new DeleteAction(id);

        try {
            deleteActionAction.validateInputs();
            Task task = new Task(deleteActionAction.getId());
            this.store.deleteTask(task);
        }
        catch (ActionValidationException e) {
            throw new ActionProcessingException(e.getMessage(), e);
        }
        catch (Exception e) {
            throw new ActionProcessingException(e.getMessage(), e.getCause());
        }
    }

    public void mark(int id, Status status) throws ActionProcessingException {
        MarkAction markAction = new MarkAction(id, status);

        try {
            markAction.validateInputs();
            Task task = new Task(markAction.getId(), markAction.getStatus());
            this.store.markTask(task);
        }
        catch (ActionValidationException e) {
            throw new ActionProcessingException(e.getMessage(), e);
        }
        catch (Exception e) {
            throw new ActionProcessingException(e.getMessage(), e.getCause());
        }
    }
}
