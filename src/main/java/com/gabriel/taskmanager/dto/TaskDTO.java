package com.gabriel.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;

    @NotBlank(message = "O título da tarefa é obrigatório.")
    private String titulo;

    @NotBlank(message = "A descrição da tarefa é obrigatória.")
    private String descricao;

    @NotNull(message = "O status da tarefa é obrigatório.") 
    private String status;

    @NotNull
    private Long userId;

}
