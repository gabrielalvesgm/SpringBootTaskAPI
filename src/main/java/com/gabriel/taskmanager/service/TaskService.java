package com.gabriel.taskmanager.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.gabriel.taskmanager.dto.TaskDTO;
import com.gabriel.taskmanager.entity.Task;
import com.gabriel.taskmanager.entity.User;
import com.gabriel.taskmanager.repository.TaskRepository;
import com.gabriel.taskmanager.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    //Injeção de dependência via construtor:
    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    /* 
    Método que cria uma nova task e associa a um usuário existente.
    Recebe DTO
    verifica se o usuário existe.
    converte de DTO para entidade
    Persiste a task via repositório
    converte de entidade para DTO
     */

    public TaskDTO createTask(@Valid TaskDTO taskDTO) {

        User user = userRepository.findById(taskDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não foi encontrado."));

        //Cria a entidade Task a partir do DTO
        Task task = new Task(); 
        task.setTitulo(taskDTO.getTitulo());
        task.setDescricao(taskDTO.getDescricao());
        task.setCreatedAt(LocalDateTime.now());
        task.setUser(user);

        //Persistir a entidade Task usando o repositório
        Task savedTask = taskRepository.save(task);

        //Converte a entidade -> DTO para retornar response
        TaskDTO response = new TaskDTO();
        response.setId(savedTask.getId());
        response.setTitulo(savedTask.getTitulo());
        response.setDescricao(savedTask.getDescricao());
        response.setUserId(savedTask.getUser().getId());

        return response;
    }

    /**
     * Dependências e quem depende:
     * - Depende de TaskRepository, UserRepository e Entities/DTOs
     * - Controller depende do Service
     */
}
