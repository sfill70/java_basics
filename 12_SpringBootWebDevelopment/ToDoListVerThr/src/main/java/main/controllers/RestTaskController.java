package main.controllers;


import main.model.TodoTask;
import main.model.TodoTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class RestTaskController {

    @Autowired
    private TodoTaskRepository todoTaskRepository;

    @GetMapping("/tasks")
    public List<TodoTask> list() {
        Iterable<TodoTask> todoTasks = todoTaskRepository.findAll();
        return new ArrayList<TodoTask>((Collection<? extends TodoTask>) todoTasks);
    }


    @DeleteMapping("/tasks")
    public List<TodoTask> clearTask() {
        todoTaskRepository.deleteAll();
        Iterable<TodoTask> todoTask = todoTaskRepository.findAll();
        return new ArrayList<TodoTask>((Collection<? extends TodoTask>) todoTask);
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> add(@Valid @RequestBody TodoTask task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(task);
        }
        TodoTask newTodoTask = todoTaskRepository.save(task);
        return ResponseEntity.ok(newTodoTask);
    }

    @GetMapping("/tasks/{id}")
    public TodoTask get(@PathVariable int id) {
        Optional<TodoTask> todoTask = todoTaskRepository.findById(id);
        if (todoTask.isPresent()) {
            return todoTask.get();
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> putTask(@Valid @RequestBody TodoTask task, @PathVariable int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(task);
        }
        task.setId(id);
        TodoTask newTodoTask = todoTaskRepository.save(task);
        return ResponseEntity.ok(newTodoTask);
    }

    @DeleteMapping("/tasks/{id}")
    public List<TodoTask> deleteTask(@PathVariable int id) {
        todoTaskRepository.deleteById(id);
        Iterable<TodoTask> optionalTodoTask = todoTaskRepository.findAll();
        return new ArrayList<TodoTask>((Collection<? extends TodoTask>) optionalTodoTask);
    }

    @GetMapping("/tasks/filter")
    @ResponseBody
    public List<TodoTask> filterLst(@RequestParam Map<String, String> allParams) {
        Iterable<TodoTask> optionalTodoTask = todoTaskRepository.findAll();
        List<TodoTask> todoTasks = new ArrayList<TodoTask>((Collection<? extends TodoTask>) optionalTodoTask);
        if (allParams.containsKey(TodoTask.getValues().get(4)) && allParams.get(TodoTask.getValues().get(4)) != null) {
            LocalDateTime dateTime = null;
            try {
                dateTime = LocalDateTime.parse(allParams.get(TodoTask.getValues().get(4)).strip());
            } catch (Exception e) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "invalid query format Deadline, you must enter - 2020-12-01T22:42"
                );
            }
            boolean isAfter = false;
            if (allParams.containsKey("date")) {
                isAfter = allParams.get("date").strip().equalsIgnoreCase("before");
            }
            for (int i = 0; i < todoTasks.size(); i++) {
                if (isAfter ? todoTasks.get(i).getDeadline().isBefore(dateTime) : todoTasks.get(i).getDeadline().isAfter(dateTime)) {
                    todoTasks.remove(todoTasks.get(i));
                    i--;
                }
            }
        }
        allParams.remove(TodoTask.getValues().get(4));
        for (Map.Entry<String, String> param : allParams.entrySet()
        ) {
            if (TodoTask.getValues().contains(param.getKey().strip())) {
                for (int i = 0; i < todoTasks.size(); i++) {
                    String value = getValue(todoTasks, param, i);
                    if (!value.toLowerCase().contains(param.getValue().strip().toLowerCase())) {
                        todoTasks.remove(todoTasks.get(i));
                        i--;
                    }
                }
            }
        }
        return todoTasks;
    }

    private String getValue(List<TodoTask> todoTasks, Map.Entry<String, String> param, int i) {
        String value = "";
        if (param.getKey().strip().equalsIgnoreCase(TodoTask.getValues().get(1))) {
            value = todoTasks.get(i).getTitle();
        } else if (param.getKey().strip().equalsIgnoreCase(TodoTask.getValues().get(2))) {
            value = todoTasks.get(i).getDescription();
        } else if (param.getKey().strip().equalsIgnoreCase(TodoTask.getValues().get(3))) {
            value = todoTasks.get(i).getPriority().toString();
        }
        return value;
    }

    @GetMapping("/hello")
    ResponseEntity<String> hello() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

}