package main.model;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskRepository {
    private static final AtomicInteger id = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, TodoTask> tasks = new ConcurrentHashMap<>();
    public static Collection<TodoTask> getAllTasks() {
        return tasks.values();
    }

    public static int addTask(TodoTask task) {
        int id = TaskRepository.id.incrementAndGet();
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public static TodoTask add(TodoTask task) {
        int id = TaskRepository.id.incrementAndGet();
        task.setId(id);
        tasks.put(id, task);
        return task;
    }

    public static Collection<TodoTask>  clearAllTasks() {
        tasks.clear();
        return tasks.values();
    }
    /* public  static Collection<TodoTask> clear(){
        return Optional.ofNullable(tasks.values());
    }*/

    public static Optional<TodoTask> getTask(int taskId) {
        return Optional.ofNullable(tasks.getOrDefault(taskId, null));
    }

    public static Optional<TodoTask> putTask(TodoTask task, int taskId) {
        tasks.getOrDefault(taskId,null).setId(taskId);
        tasks.getOrDefault(taskId, null).setTitle(task.getTitle());
        tasks.getOrDefault(taskId, null).setDescription(task.getDescription());
        tasks.getOrDefault(taskId, null).setPriority(task.getPriority());
        tasks.getOrDefault(taskId, null).setDeadline(task.getDeadline());
        return Optional.ofNullable(tasks.getOrDefault(taskId, null));
    }

    public static Optional<TodoTask> deleteTaskById(int taskId) {
        return Optional.ofNullable(tasks.remove(taskId));
    }

}

