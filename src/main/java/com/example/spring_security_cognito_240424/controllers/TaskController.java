package com.example.spring_security_cognito_240424.controllers;

import com.example.spring_security_cognito_240424.models.Task;
import com.example.spring_security_cognito_240424.services.TaskService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public String createTask(@ModelAttribute Task task) {
        OidcUser user = (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        task.setCognitoUsername(user.getName());
        taskService.createTask(task);
        return "redirect:/tasks";
    }

    @GetMapping
    public String getTasks(Model model) {
        OidcUser user = (OidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Task> tasks = taskService.getTasks(user.getName());
        model.addAttribute("tasks", tasks);
        return "task";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }
}
