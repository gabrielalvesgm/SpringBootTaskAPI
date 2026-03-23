package com.gabriel.taskmanager.service;

import org.springframework.stereotype.Service;

import com.gabriel.taskmanager.dto.UserDTO;
import com.gabriel.taskmanager.entity.User;
import com.gabriel.taskmanager.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {

    //Dependência: Repositório fornece persistência e consultas.
    private final UserRepository userRepository;

    //Injenção de dependência via construtor.
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
    Método que busca um usuário pelo ID
    Fluxo -> 
    Service -> Repositório
    se não existe -> exception
    Se existe -> converte para DTO
     */

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        //
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNome(user.getNome());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedAt(user.getCreatedAt());

        return userDTO;
    }

    /*
    - RECEBE O DTO
    - CONVERTE DE DTO -> ENTIDADE
    - PERSISTE A ENTIDADE VIA REPOSITÓRIO
    - CONVERTE DE ENTIDADE -> DTO
    - RETORNA O DTO.
    */
    public UserDTO createUser(@Valid UserDTO userDTO) {

        // DTO -> ENTIDADE
        User user = new User();
        user.setNome(userDTO.getNome());
        user.setEmail(userDTO.getEmail());

        User savedUser = userRepository.save(user);

        // ENTIDADE -> DTO
        UserDTO responseDTO = new UserDTO();
        responseDTO.setId(savedUser.getId());
        responseDTO.setNome(savedUser.getNome());
        responseDTO.setEmail(savedUser.getEmail());
        responseDTO.setCreatedAt(savedUser.getCreatedAt());

        return responseDTO;
    }

     /**
     * Dependências e quem depende:
     * - Depende de UserRepository e Entities/DTOs
     * - Controller depende do Service
     */

}
