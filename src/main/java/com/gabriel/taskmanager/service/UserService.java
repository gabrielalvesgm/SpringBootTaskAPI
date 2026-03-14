package com.gabriel.taskmanager.service;


import com.gabriel.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.gabriel.taskmanager.dto.UserDTO;
import com.gabriel.taskmanager.entity.User;
import jakarta.validation.Valid;

@Service
public class UserService {

    //Repositório genérico para referência
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(@Valid UserDTO userDTO) {

        //DTO -> ENTIDADE
        User user = new User();
        user.setNome(userDTO.getNome());
        user.setEmail(userDTO.getEmail());
        
        User savedUser = userRepository.save(user);

        //ENTIDADE -> DTO
        UserDTO responseDTO = new UserDTO();
        responseDTO.setId(savedUser.getId());
        responseDTO.setNome(savedUser.getNome());
        responseDTO.setEmail(savedUser.getEmail());
        responseDTO.setCreatedAt(savedUser.getCreatedAt());

        return responseDTO;
    }

}
