package com.tasktracker.cli;

import com.google.gson.Gson;
import com.tasktracker.cli.exception.TaskStoreException;
import com.tasktracker.cli.model.Store;
import com.tasktracker.cli.model.Task;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

public class TaskStore {
    private static final String STORE_FILE = "taskStore.json";
    private static final String STORE_PATH = "data/taskStore.json";

    Gson gson;

    public TaskStore() {
        gson = new Gson();
    }

    public int getTaskCount() throws TaskStoreException {
        // read count from store
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream(STORE_PATH))))) {
            Store data = gson.fromJson(reader, Store.class);
            return data.getCount();
        }
        catch (NullPointerException e) {
            throw new TaskStoreException("Unable to read task count.", e);
        }
        catch (IOException e) {
            throw new TaskStoreException("Unable to read from store.", e);
        }
    }

    public void addTask(Task task) throws TaskStoreException {
        System.out.println("Add: saving new task - \"" + task.toShortString() + "\"");
        Store savedData;
        Path writableStorePath = Paths.get(System.getProperty("user.home"), "taskStore.json");

        // ensure the writable store exists (copy from class resources if necessary)
        if (Files.notExists(writableStorePath)) {
            System.out.println("Store not found in writable location. Extracting default store...");
            try (InputStream inputStream = Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream(STORE_PATH))) {
                Files.copy(inputStream, writableStorePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new TaskStoreException("Unable to extract default store.", e);
            }
        }

        // read the tasks from the writable store
        try (BufferedReader reader = Files.newBufferedReader(writableStorePath)) {
            savedData = gson.fromJson(reader, Store.class);
        } catch (IOException e) {
            throw new TaskStoreException("Unable to read from store.", e);
        }

        // setup next task with correct id (next size of list) and
        // append the new task to the list to create "new" Store object
        List<Task> nextTasks = savedData.getTasks();
        Task finalAddTask = new Task(nextTasks.size() + 1, task);
        nextTasks.add(finalAddTask);
        Store nextStore = new Store(nextTasks);
        Store nextJson = gson.fromJson(gson.toJson(nextStore), Store.class);

        // write the updated store back to the writable store
        try (BufferedWriter writer = Files.newBufferedWriter(writableStorePath)) {
            gson.toJson(nextJson, writer);
        } catch (IOException e) {
            throw new TaskStoreException("Unable to write to store.", e);
        }

        System.out.println("Add: successfully added new task with id [" + finalAddTask.getId() + "]");
    }
}
