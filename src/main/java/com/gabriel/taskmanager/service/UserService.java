package com.gabriel.taskmanager.service;

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

    //criando um novo usuário
    public UserDTO createUser(@Valid UserDTO userDTO) {
        //converte DTO para -> Entidade
        User user = new User();
        user.setNome(userDTO.getNome());
        user.setEmail(userDTO.getEmail());

    //Método para salvar no repositório.
    User savedUser = userRepository.save(user);

    //converte entidade -> DTO e retorna.
    UserDTO savedUserDTO= new UserDTO();
    savedUserDTo.setID(savedUser.getId());



    return savedUserDTO;
    }   

}
