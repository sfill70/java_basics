package main.exception;

import main.model.Priority;
import main.model.TodoTask;

import java.time.LocalDateTime;

public class MyRequestException extends RuntimeException {
    String message;
    TodoTask task;

    public MyRequestException(TodoTask task) {
        this.task = task;
    }

    public TodoTask getTask() {
        return task;
    }

    public String getBadValue(TodoTask task) {
        String title = "", description = "", priority = "", deadline = "";
        if (task.getTitle().isEmpty()) {
            title = "title isEmpty()";
        }
        if (task.getDescription().isEmpty()) {
            description = "Description isEmpty()";
        }
        if (!task.getPriority().toString().isEmpty()) {
            if (task.getPriority() != Priority.HIGH || task.getPriority() != Priority.MEDIUM || task.getPriority() != Priority.LOW) {
                priority = "Priority isBad";
            }
        } else {
            priority = "Priority isEmpty";
        }
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(task.getDeadline().toString());
        } catch (Exception e) {
            deadline = "deadline isBad";
        }

        return "error in - " + String.join(", ", title, description, priority, deadline);
    }
}
