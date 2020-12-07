package main.controllers;


import main.exception.EntityNotFoundException;
import main.exception.MyRequestException;
import main.model.TodoTask;
import main.model.TodoTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/tasks")
public class RestTaskController {

    @Autowired
    private TodoTaskRepository todoTaskRepository;

    @GetMapping("")
    public List<TodoTask> list() {
        return todoTaskRepository.findAll();
    }


    @DeleteMapping("")
    public List<TodoTask> clearTask() {
        todoTaskRepository.deleteAll();
        return todoTaskRepository.findAll();
    }

    @PostMapping("")
    public ResponseEntity<TodoTask> add(@Valid @RequestBody TodoTask task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyRequestException(task);
        }
        TodoTask newTodoTask = todoTaskRepository.save(task);
        return ResponseEntity.ok(newTodoTask);
    }

    @GetMapping("/{id}")
    public TodoTask get(@PathVariable int id) {
        return todoTaskRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putTask(@Valid @RequestBody TodoTask task, @PathVariable int id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new MyRequestException(task);
        }
        task.setId(id);
        TodoTask newTodoTask = todoTaskRepository.save(task);
        return ResponseEntity.ok(newTodoTask);
    }

    @DeleteMapping("/{id}")
    public List<TodoTask> deleteTask(@PathVariable int id) {
        todoTaskRepository.deleteById(id);
        Iterable<TodoTask> optionalTodoTask = todoTaskRepository.findAll();
        return new ArrayList<TodoTask>((Collection<? extends TodoTask>) optionalTodoTask);
    }

    @GetMapping("/search")
    @ResponseBody
    public List<TodoTask> filterLst(@RequestParam Map<String, String> allParams) {
        Iterable<TodoTask> optionalTodoTask = todoTaskRepository.findAll();
        List<TodoTask> todoTasks = new ArrayList<TodoTask>((Collection<? extends TodoTask>) optionalTodoTask);
        if (allParams.containsKey(TodoTask.getValues().get(4)) && !allParams.get(TodoTask.getValues().get(4)).isEmpty()) {
            LocalDateTime dateTime = null;
            dateTime = LocalDateTime.parse(allParams.get(TodoTask.getValues().get(4)).strip());

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