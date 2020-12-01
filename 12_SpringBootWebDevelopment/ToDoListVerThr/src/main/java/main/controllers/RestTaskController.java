package main.controllers;


import main.model.TodoTask;
import main.model.TodoTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class RestTaskController {

    @Autowired
    private TodoTaskRepository todoTaskRepository;

    @GetMapping("/tasks")
    public List<TodoTask> list() {
        Iterable<TodoTask> optionalTodoTask = todoTaskRepository.findAll();
        List<TodoTask> todoTasks = new ArrayList<TodoTask>((Collection<? extends TodoTask>) optionalTodoTask);
        return todoTasks;
    }

    @DeleteMapping("/tasks")
    public List<TodoTask> clearTask() {
        todoTaskRepository.deleteAll();
        Iterable<TodoTask> optionalTodoTask = todoTaskRepository.findAll();
        return new ArrayList<TodoTask>((Collection<? extends TodoTask>) optionalTodoTask);
    }

    @PostMapping("/tasks")
    public TodoTask add(@Valid @RequestBody TodoTask task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return task;
        }
        TodoTask newTodoTask = todoTaskRepository.save(task);
        return newTodoTask;
    }

    @GetMapping("/tasks/{id}")
    public Optional<TodoTask> get(@PathVariable int id) {
        Optional<TodoTask> todoTask = todoTaskRepository.findById(id);
        return todoTask;
    }

    @PutMapping("/tasks/{id}")
    public Optional<TodoTask> putTask(@Valid @RequestBody TodoTask task, @PathVariable int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return todoTaskRepository.findById(id);
        }
        task.setId(id);
        TodoTask newTodoTask = todoTaskRepository.save(task);
        return todoTaskRepository.findById(id);
    }

    @DeleteMapping("/tasks/{id}")
    public List<TodoTask> deleteTask(@PathVariable int id) {
        todoTaskRepository.deleteById(id);
        Iterable<TodoTask> optionalTodoTask = todoTaskRepository.findAll();
        return new ArrayList<TodoTask>((Collection<? extends TodoTask>) optionalTodoTask);
    }



    /*    @PostMapping("/tasks")
//    @RequestMapping(value = "/tasks/", method = RequestMethod.POST, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = {"application/json"})
    public int add(@RequestBody TodoTask task) {
        return TaskRepository.addTask(task);
    }*/
}