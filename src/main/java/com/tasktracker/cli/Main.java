package com.tasktracker.cli;

import com.tasktracker.cli.model.Update;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("task-cli requires at least an action arg!");
            return;
        }

        ActionProcessor processor = new ActionProcessor();

        // read user provided args
        String action = args[0];
        String[] actionRestArgs = Arrays.stream(args, 1, args.length).toArray(String[]::new);

        // TODO: validate base args, has to be a string value

        switch (action) {
            case "add": {
                String taskStatus = actionRestArgs.length > 0 ? actionRestArgs[0] : "";
                processor.add(taskStatus);
                break;
            }
            case "update": {
                // TODO: args required, validate args
                //  id - int and id present in json
                //  status - String
                int taskId = Integer.parseInt(actionRestArgs[0]);
                String taskStatus = actionRestArgs[1];
                Update update = new Update(taskId, taskStatus);

                // TODO: wire up validation here
                try {
                    update.validateInputs();
                } catch (Exception e) {
                    System.err.println("Ooops");
                    return;
                }

                System.out.println("updating task '" + taskId + "' with status '" + taskStatus + "'");
                break;
            }
            case "delete": {
                // TODO: arg required, validate arg - has to be integer and id present in json
                int taskId = Integer.parseInt(actionRestArgs[0]);
                System.out.println("deleting task '" + taskId + "'");
                break;
            }
            case "mark-in-progress": {
                // TODO: arg required, validate arg - has to be integer and id present in json
                int taskId = Integer.parseInt(actionRestArgs[0]);
                System.out.println("marking " + taskId + " in progress");
                break;
            }
            case "mark-done": {
                // TODO: arg required, validate arg - has to be integer and id present in json
                int taskId = Integer.parseInt(actionRestArgs[0]);
                System.out.println("marking " + taskId + " done");
                break;
            }
            case "list": {
                String status = actionRestArgs.length > 0 ? actionRestArgs[0] : "";
                processor.list(status);
                break;
            }
            default: {
                System.err.println("Unknown action, select from [add | update | delete | mark-in-progress | mark-done | list]");
            }
        }
    }
}