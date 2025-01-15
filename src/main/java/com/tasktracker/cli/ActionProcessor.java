package com.tasktracker.cli;

import com.tasktracker.cli.exception.TaskStoreException;
import com.tasktracker.cli.model.Add;
import com.tasktracker.cli.model.Status;
import com.tasktracker.cli.model.Task;

/**
 * ActionProcessor is responsible for generating an Action and Task based on the users input.
 * Once the Action is generated/validated, the Task is passed to the TaskStore for storage.
 * */
public final class ActionProcessor {
    TaskStore store;

    public ActionProcessor() {
        this.store = new TaskStore();
    }

    // TODO: how do I handle errors from store or validation failures?
    //  throw and catch error in main?
    //  types of error (StoreError and TaskProcessorValidationException)

    public void add(String taskStatus) {
        Add addAction;

        try {
            addAction = new Add(taskStatus);
            addAction.validate();
            Task task = new Task(addAction.getDescription(), Status.TODO);
            this.store.addTask(task);
        }
//        catch (TaskProcessorValidationException e) {
//            throw new TaskProcessorValidationException(e);
//        }
        // TODO: may decide to throw exceptions to parent and log
        //  that way we can possibly exit the main loop with a valid return code.
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

    public void processUpdate(int id, String status) {

    }
}
