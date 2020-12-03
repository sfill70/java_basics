package main.controllers;

import main.model.TodoTask;
import main.model.TodoTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class MvcTaskController {

    @Autowired
    private TodoTaskRepository todoTaskRepository;


    @GetMapping("/")
    public String viewTaskAll(Model model) {
        model.addAttribute("todoTask", new TodoTask());
        Iterable<TodoTask> optionalTodoTask = todoTaskRepository.findAll();
        List<TodoTask> todoTasks = new ArrayList<TodoTask>((Collection<? extends TodoTask>) optionalTodoTask);
        model.addAttribute("tasks", todoTasks);
        model.addAttribute("tasksCount", todoTasks.size());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String taskFormSubmit(@Valid @ModelAttribute TodoTask todoTask, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "заполните все поля");
            return "redirect:/";
        }
        todoTaskRepository.save(todoTask);
        return "redirect:/";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String clearTask(Model model) {
        todoTaskRepository.deleteAll();
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String viewTask(@PathVariable int id, Model model) {
        Optional<TodoTask> todoTask = todoTaskRepository.findById(id);
        if (todoTask.isPresent()) {
            TodoTask task = todoTask.get();
            String deadline = task.getDeadline().toString().replace("T", " Time - ");
            model.addAttribute("task", task);
            model.addAttribute("view", true);
            model.addAttribute("title", task.getTitle());
            model.addAttribute("description", task.getDescription());
            model.addAttribute("priority", task.getPriority());
            model.addAttribute("deadline", deadline);
        } else {
            String answer = "the page does not exist";
            model.addAttribute("view", false);
            model.addAttribute("title", answer);
        }
        return "task_view";
    }

    @RequestMapping(value = "/put/{id}", method = RequestMethod.GET)
    public String taskViewPut(@PathVariable int id, Model model) {
        Optional<TodoTask> task = todoTaskRepository.findById(id);
        if (task.isPresent()) {
            TodoTask todoTask = task.get();
            model.addAttribute("todoTask", todoTask);
            model.addAttribute("view", true);
        } else {
            String answer = "the page does not exist";
            model.addAttribute("view", false);
            model.addAttribute("title", answer);
        }
        return "task_put";
    }

    @RequestMapping(value = "/put/{id}", method = RequestMethod.POST)
    public String taskPut(@PathVariable int id, @Valid @ModelAttribute TodoTask todoTask, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/put/{id}";
        }
        todoTask.setId(id);
        todoTaskRepository.save(todoTask);
        return "redirect:/{id}";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String deleteTask(@PathVariable int id, Model model) {
        todoTaskRepository.deleteById(id);
        return "redirect:/";
    }
}