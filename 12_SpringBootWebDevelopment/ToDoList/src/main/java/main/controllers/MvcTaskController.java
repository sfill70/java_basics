package main.controllers;

import main.model.TaskRepository;
import main.model.TodoTask;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MvcTaskController {
    @RequestMapping("/")
    public String index(Model model) {
        List<TodoTask> tasks = new ArrayList<>(TaskRepository.getAllTasks());
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksCount", tasks.size());
        return "index";
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String taskForm(Model model) {
        model.addAttribute("todoTask", new TodoTask());
        List<TodoTask> tasks = new ArrayList<>(TaskRepository.getAllTasks());
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksCount", tasks.size());
        return "index";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String taskSubmit(@ModelAttribute TodoTask todoTask, Model model) {
        TaskRepository.addTask(todoTask);
        model.addAttribute("todoTask", todoTask);
        List<TodoTask> tasks = new ArrayList<>(TaskRepository.getAllTasks());
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksCount", tasks.size());
        return "redirect:/";
    }




   /* @RequestMapping("/addTask")
    public String addTask(Model model) {
        List<TodoTask> tasks = new ArrayList<>(TaskRepository.getAllTasks());
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksCount", tasks.size());
        return "redirect:/addTask";
    }*/
    /*@RequestMapping(value = "/addTask/", method = RequestMethod.GET)
    public ModelAndView addTask(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        ModelAndView result = new ModelAndView("/addTask/");
        result.addObject("todoTask", new TodoTask());
        return result;
    }

    @RequestMapping(value = "/addTask/", method = RequestMethod.POST)
    public String add(TodoTask todoTask) throws UnsupportedEncodingException {
        TodoTask task = new TodoTask();
        task.setTitle(todoTask.getTitle());
        task.setDescription(todoTask.getDescription());
        task.setPriority(todoTask.getPriority());
        task.setDeadline(todoTask.getDeadline());
        TaskRepository.addTask(task);
        return "redirect:/addTask/";
    }*/
/*    @RequestMapping(value="/addTask", method=RequestMethod.GET)
    public String taskForm(Model model) {
        model.addAttribute("todoTask", new TodoTask());
        List<TodoTask> tasks = new ArrayList<>(TaskRepository.getAllTasks());
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksCount", tasks.size());
        return "addTask";
    }

    @RequestMapping(value="/addTask", method=RequestMethod.POST)
    public String taskSubmit(@ModelAttribute TodoTask todoTask, Model model) {
        TaskRepository.addTask(todoTask);
        model.addAttribute("todoTask", todoTask);
        List<TodoTask> tasks = new ArrayList<>(TaskRepository.getAllTasks());
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksCount", tasks.size());
        return "addTask";
    }*/

}