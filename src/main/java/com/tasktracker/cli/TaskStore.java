package com.tasktracker.cli;

import com.google.gson.Gson;
import com.tasktracker.cli.exception.TaskStoreException;
import com.tasktracker.cli.model.ListAction;
import com.tasktracker.cli.model.Status;
import com.tasktracker.cli.model.Store;
import com.tasktracker.cli.model.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TaskStore {
    private static final String STORE_FILE = "taskStore.json";
    private static final String STORE_PATH = "data/taskStore.json";
    /** The directory that the store is copied to on the user's device */
    private static final Path LOCAL_STORE_PATH = Paths.get(System.getProperty("user.home"), STORE_FILE);

    Gson gson;

    public TaskStore() {
        gson = new Gson();
    }

    public void addTask(Task task) throws TaskStoreException {
        System.out.println("Add: saving new task - \"" + task.getDescription() + "\"...");
        Store savedData = readStore();

        // setup next task with correct id (next size of list) and
        // append the new task to the list to create "new" Store object
        List<Task> nextTasks = savedData.getTasks();
        Task finalAddTask = new Task(nextTasks.size() + 1, task);
        nextTasks.add(finalAddTask);

        // create an updated Store object
        Store nextStore = new Store(nextTasks);
        Store nextJson = gson.fromJson(gson.toJson(nextStore), Store.class);

        writeTasksToStore(nextJson);
        System.out.println("Add: successfully saved new task with id [" + finalAddTask.getId() + "]");
    }

    public List<Task> getTasks(ListAction action) throws TaskStoreException {
        System.out.println("List: retrieving tasks...");
        Store savedData = readStore();
        Status actionStatus = action.getStatus();
        List<Task> tasks = savedData.getTasks();

        if (actionStatus == null) {
            return tasks;
        } else {
            System.out.println("List: filtering tasks by status: " + actionStatus);
            return tasks.stream().filter(t -> t.getStatus() == actionStatus).collect(Collectors.toList());
        }
    }

    public void updateTask(Task task) throws TaskStoreException {
        Store savedData = readStore();
        List<Task> currentTasks = savedData.getTasks();

        if (currentTasks.isEmpty()) {
            throw new TaskStoreException("Task list is empty");
        }

        var filteredItems = currentTasks.stream().filter(t -> t.getId() == task.getId()).toList();
        if (filteredItems.isEmpty()) {
            // did not find item with id user provided
            throw new TaskStoreException("Task id '" + task.getId() + "' does not exist");
        }

        // insert updated item into list at the place of the original item
        List<Task> nextTasks = currentTasks.stream().map(t -> {
            // copy over original task's id/createdAt, use the updated task for other values
            return t.getId() != task.getId() ? t : new Task(t.getId(), t.getCreatedAt(), task);
        }).toList();

        // create an updated Store object
        Store nextStore = new Store(nextTasks);
        Store nextJson = gson.fromJson(gson.toJson(nextStore), Store.class);

        writeTasksToStore(nextJson);
        System.out.println("Update: successfully updated task id [" + task.getId() + "] with description - " + task.getDescription());
    }

    private static void verifyStoreLoaded() throws TaskStoreException {
        // ensure the writable store exists (copy from class resources if necessary)
        if (Files.notExists(LOCAL_STORE_PATH)) {
            System.out.println("Store not found in writable location. Extracting default store...");
            try (InputStream inputStream = Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream(STORE_PATH))) {
                Files.copy(inputStream, LOCAL_STORE_PATH, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new TaskStoreException("Unable to extract default store.", e);
            }
        }
    }

    private Store readStore() throws TaskStoreException {
        Store savedData;

        verifyStoreLoaded();

        // read the tasks from the writable store
        try (BufferedReader reader = Files.newBufferedReader(LOCAL_STORE_PATH)) {
            savedData = gson.fromJson(reader, Store.class);
        } catch (IOException e) {
            throw new TaskStoreException("Unable to read from store.", e);
        }
        return savedData;
    }

    private void writeTasksToStore(Store nextJson) throws TaskStoreException {
        // write the updated store back to the writable store
        try (BufferedWriter writer = Files.newBufferedWriter(LOCAL_STORE_PATH)) {
            gson.toJson(nextJson, writer);
        } catch (IOException e) {
            throw new TaskStoreException("Unable to write to store.", e);
        }
    }
}
