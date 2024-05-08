package com.example.spring_security_cognito_240424.services;

import com.example.spring_security_cognito_240424.models.Task;
import com.example.spring_security_cognito_240424.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getTasks(String cognitoUsername) {
        return taskRepository.findByCognitoUsername(cognitoUsername);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
