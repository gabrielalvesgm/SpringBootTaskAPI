package com.gabriel.taskmanager.service;

import com.gabriel.taskmanager.dto.TaskDTO;
import com.gabriel.taskmanager.repository.TaskRepository;
import com.gabriel.taskmanager.repository.UserRepository;
import com.gabriel.taskmanager.entity.Task;
import com.gabriel.taskmanager.entity.User;

import jakarta.validation.Valid;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public TaskDTO createTask(@Valid TaskDTO taskDTO) {

        User user = userRepository.findById(taskDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        TaskDTO response = new TaskDTO();
        Task task = new Task();

        task.setTitulo(taskDTO.getTitulo());
        task.setDescricao(taskDTO.getDescricao());
        task.setStatus(taskDTO.getStatus());
        task.setCreatedAt(LocalDateTime.now());
        task.setUser(user);

        Task savedTask = taskRepository.save(task);

        response = new TaskDTO();

        response.setId(savedTask.getId());
        response.setTitulo(savedTask.getTitulo());
        response.setDescricao(savedTask.getDescricao());
        response.setStatus(savedTask.getStatus());
        response.setUserId(savedTask.getUser().getId());
        response.setCreatedAt(savedTask.getCreatedAt());

        return response;
    }

}
