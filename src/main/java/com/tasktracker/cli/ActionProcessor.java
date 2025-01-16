package com.tasktracker.cli;

import com.tasktracker.cli.exception.ActionProcessingException;
import com.tasktracker.cli.exception.ActionValidationException;
import com.tasktracker.cli.model.Add;
import com.tasktracker.cli.model.List;
import com.tasktracker.cli.model.Status;
import com.tasktracker.cli.model.Task;

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
        Add addAction = new Add(taskStatus);

        try {
            addAction.validate();
            Task task = new Task(addAction.getDescription(), Status.TODO);
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
        List listAction = new List(rawStatus);
        Task[] tasks = {};

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

        if (tasks.length == 0) {
            System.out.println("List: no tasks found");
            return;
        }

        System.out.println("List: retrieved " + tasks.length + " task(s)");
        for (Task t : tasks) {
            System.out.println("\t - " + t.toShortString());
        }
    }

    public void update(int id, String status) throws ActionProcessingException {

    }
}
