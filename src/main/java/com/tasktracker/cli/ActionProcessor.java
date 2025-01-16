package com.tasktracker.cli;

import com.tasktracker.cli.exception.ActionValidationException;
import com.tasktracker.cli.exception.TaskStoreException;
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

    // TODO: how do I handle errors from store or validation failures?
    //  throw and catch error in main?

    public void add(String taskStatus) {
        Add addAction = new Add(taskStatus);

        try {
            addAction.validate();
            Task task = new Task(addAction.getDescription(), Status.TODO);
            this.store.addTask(task);
        }
        // TODO: may decide to throw exceptions to parent and log there instead,
        //  that way we can possibly exit the main loop with a valid return code.
        catch (ActionValidationException e) {
            System.err.println("Add Failed.");
            System.err.println("ActionValidationException: " + e.getMessage());
        }
        catch (TaskStoreException e) {
            System.err.println("Add Failed.");
            System.err.println("TaskStoreException - Root issue: " + e.getCause().getMessage());
            System.err.println("TaskStoreException - Resulting issue: " + e.getMessage());
        }
        catch (Exception e) {
            System.err.println("Add Failed.");
            System.err.println("Exception - Root issue: " + e.getCause().getMessage());
            System.err.println("Exception - Resulting issue: " + e.getMessage());
        }
    }

    public void list(String rawStatus) {
        // temporary action until rawStatus is validated
        List listAction = new List(rawStatus);
        Task[] tasks = {};

        try {
            listAction.validate();
            tasks = this.store.getTasks(listAction);
        }
        // TODO: may decide to throw exceptions to parent and log there instead,
        //  that way we can possibly exit the main loop with a valid return code.
        catch (ActionValidationException e) {
            System.err.println("List Failed.");
            System.err.println("ActionValidationException: " + e.getMessage());
            return;
        }
        catch (TaskStoreException e) {
            System.err.println("List Failed.");
            System.err.println("TaskStoreException - Root issue: " + e.getCause().getMessage());
            System.err.println("TaskStoreException - Resulting issue: " + e.getMessage());
            return;
        }
        catch (Exception e) {
            System.err.println("List Failed.");
            System.err.println("Exception - Root issue: " + e.getCause().getMessage());
            System.err.println("Exception - Resulting issue: " + e.getMessage());
            return;
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

    public void update(int id, String status) {

    }
}
