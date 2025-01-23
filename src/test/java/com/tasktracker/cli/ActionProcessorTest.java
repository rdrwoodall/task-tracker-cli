package com.tasktracker.cli;

import com.tasktracker.cli.exceptions.ActionProcessingException;
import com.tasktracker.cli.exceptions.TaskStoreException;
import com.tasktracker.cli.models.Status;
import com.tasktracker.cli.models.Task;
import com.tasktracker.cli.models.actions.ListAction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

class ActionProcessorTest {
    static ActionProcessor processor;
    static TaskStore storeMock;

    @BeforeAll
    static void initAll() {
        processor = new ActionProcessor();
        storeMock = new TaskStore();
    }

    //region Add

    @Test
    void addActionThrowsWhenDescriptionIsNotProvided() {
        assertThrows(ActionProcessingException.class, () -> processor.add(""));
    }

    @Test
    void addActionThrowsWhenDescriptionIsProvidedButIsWhitespaceOnly() {
        assertThrows(ActionProcessingException.class, () -> processor.add("   "));
    }

    @Test
    void addActionProperlyCapturesAndThrowsActionProcessorExceptionWhenStoreThrows() throws Exception {
        storeMock = Mockito.mock(TaskStore.class);

        doThrow(TaskStoreException.class).when(storeMock)
                .addTask(any());

        assertThrows(TaskStoreException.class, () -> storeMock.addTask(new Task("My new task")));
    }

    //endregion

    //region List

    @Test
    void listActionOutputsMessageOnCompletion() throws Exception {
        String output = tapSystemOut(() -> processor.list(ListAction.STATUS_STRING_TODO));
        assertFalse(output.trim().isBlank());
    }

    @Test
    void listActionDoesNotThrowWhenStatusIsBlank() {
        assertDoesNotThrow(() -> processor.list(""));
    }

    @Test
    void listActionDoesNotThrowWhenStatusIsDone() {
        assertDoesNotThrow(() -> processor.list(ListAction.STATUS_STRING_DONE));
    }

    @Test
    void listActionDoesNotThrowWhenStatusIsTodo() {
        assertDoesNotThrow(() -> processor.list(ListAction.STATUS_STRING_TODO));
    }

    @Test
    void listActionDoesNotThrowWhenStatusIsInProgress() {
        assertDoesNotThrow(() -> processor.list(ListAction.STATUS_STRING_IN_PROGRESS));
    }

    @Test
    void listActionThrowsWhenStatusIsUnknownValue() {
        assertThrows(ActionProcessingException.class, () -> processor.list("unknown"));
    }

    //endregion

    //region Update

    @Test
    void updateActionThrowsWhenIdIsNotProvided() {
        // id internally is set to -1 by default
        assertThrows(ActionProcessingException.class, () -> processor.update(-1, ""));
    }

    @Test
    void updateActionThrowsWhenIdIsNotInTaskStore() {
        // id internally is set to -1 by default
        assertThrows(ActionProcessingException.class, () -> processor.update(10, "My update"));
    }

    @Test
    void updateActionThrowsWhenDescriptionIsNotProvided() {
        // id internally is set to -1 by default
        assertThrows(ActionProcessingException.class, () -> processor.update(1, ""));
    }

    @Test
    void updateActionThrowsWhenDescriptionIsProvidedButWhitespaceOnly() {
        // id internally is set to -1 by default
        assertThrows(ActionProcessingException.class, () -> processor.update(1, "   "));
    }

    @Test
    void updateActionCapturesAndThrowsActionProcessorExceptionWhenStoreThrows() throws Exception {
        storeMock = Mockito.mock(TaskStore.class);

        doThrow(TaskStoreException.class).when(storeMock)
                .updateTask(any());

        assertThrows(TaskStoreException.class, () -> storeMock.updateTask(new Task(1, "My updated task")));
    }

    //endregion

    //region Delete

    @Test
    void deleteActionThrowsWhenIdIsNotProvided() {
        // id internally is set to -1 by default
        assertThrows(ActionProcessingException.class, () -> processor.delete(-1));
    }

    @Test
    void deleteActionThrowsWhenIdIsNotInTaskStore() {
        // id internally is set to -1 by default
        assertThrows(ActionProcessingException.class, () -> processor.delete(10));
    }

    @Test
    void deleteActionCapturesAndThrowsActionProcessorExceptionWhenStoreThrows() throws Exception {
        storeMock = Mockito.mock(TaskStore.class);

        doThrow(TaskStoreException.class).when(storeMock)
                .deleteTask(any());

        assertThrows(TaskStoreException.class, () -> storeMock.deleteTask(new Task(1)));
    }

    //endregion

    //region Mark

    @Test
    void markActionThrowsWhenIdIsNotProvided() {
        // id internally is set to -1 by default
        assertThrows(ActionProcessingException.class, () -> processor.mark(-1, Status.IN_PROGRESS));
    }

    @Test
    void markActionThrowsWhenIdIsNotInTaskStore() {
        assertThrows(ActionProcessingException.class, () -> processor.mark(10, Status.IN_PROGRESS));
    }

    @Test
    void markActionCapturesAndThrowsActionProcessorExceptionWhenStoreThrows() throws Exception {
        storeMock = Mockito.mock(TaskStore.class);

        doThrow(TaskStoreException.class).when(storeMock)
                .markTask(any());

        assertThrows(TaskStoreException.class, () -> storeMock.markTask(new Task(1, Status.IN_PROGRESS)));
    }

    //endregion
}