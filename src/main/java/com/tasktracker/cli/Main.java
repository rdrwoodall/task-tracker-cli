package com.tasktracker.cli;

import com.tasktracker.cli.exception.ActionProcessingException;

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
                try {
                    processor.add(taskStatus);
                } catch (ActionProcessingException e) {
                    handleException(action, e);
                }
                break;
            }
            case "update": {
                int taskId = actionRestArgs.length > 0 ? Integer.parseInt(actionRestArgs[0]) : -1;
                String taskStatus = actionRestArgs.length > 1 ? actionRestArgs[1] : "";
                try {
                    processor.update(taskId, taskStatus);
                } catch (ActionProcessingException e) {
                    handleException(action, e);
                }
                break;
            }
            case "delete": {
                int taskId = actionRestArgs.length > 0 ? Integer.parseInt(actionRestArgs[0]) : -1;
                String taskStatus = actionRestArgs.length > 1 ? actionRestArgs[1] : "";
                try {
                    processor.delete(taskId);
                } catch (ActionProcessingException e) {
                    handleException(action, e);
                }
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
            case "mark-todo": {
                // TODO: arg required, validate arg - has to be integer and id present in json
                int taskId = Integer.parseInt(actionRestArgs[0]);
                System.out.println("marking " + taskId + " todo");
                break;
            }
            case "list": {
                String status = actionRestArgs.length > 0 ? actionRestArgs[0] : "";
                try {
                    processor.list(status);
                } catch (ActionProcessingException e) {
                    handleException(action , e);
                }
                break;
            }
            default: {
                System.err.println("Unknown action, select from [add | update | delete | mark-in-progress | mark-done | list]");
            }
        }
    }

    private static void handleException(String action, ActionProcessingException e) {
        String rootIssueMessage = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
        String resultingIssueMessage = e.getMessage();

        System.err.println("Action '" + action + "' failed!");
        System.err.println("ActionProcessingException - Root issue: " + rootIssueMessage);
        if (!rootIssueMessage.equals(resultingIssueMessage)) {
            System.err.println("ActionProcessingException - Resulting issue: " + resultingIssueMessage);
        }

        System.exit(1);
    }
}