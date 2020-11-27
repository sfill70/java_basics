package main.controllers;


import main.model.TaskRepository;
import main.model.TodoTask;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
public class RestTaskController {

    @GetMapping("/tasks")
    public List<TodoTask> list() {
        return List.copyOf(TaskRepository.getAllTasks());
    }

    @DeleteMapping("/tasks")
    public List<TodoTask> clearTask() {
        return List.copyOf(TaskRepository.clearAllTasks());
    }

    //    @RequestMapping(value = "/tasks/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {"application/json"})
    @PostMapping("/tasks")
    public TodoTask add(@RequestBody TodoTask task) {
        return TaskRepository.add(task);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TodoTask> get(@PathVariable int id) {
        return ResponseEntity.of(TaskRepository.getTask(id));
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TodoTask> putTask(@RequestBody TodoTask task, @PathVariable int id) {
        return ResponseEntity.of(TaskRepository.putTask(task, id));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<TodoTask> deleteTask(@PathVariable int id) {
        return ResponseEntity.of(TaskRepository.deleteTaskById(id));
    }

    /*    @PostMapping("/tasks")
//    @RequestMapping(value = "/tasks/", method = RequestMethod.POST, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {"application/json"})
    public int add(@RequestBody TodoTask task) {
        return TaskRepository.addTask(task);
    }*/
}