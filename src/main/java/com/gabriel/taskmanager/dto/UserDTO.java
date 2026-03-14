package com.gabriel.taskmanager.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Email
    @NotBlank(message = "O email é obrigatório")
    private String email;

    private LocalDateTime createdAt;


}
