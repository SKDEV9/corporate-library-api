package com.SKDEV9.corporate_library_api.service;

import com.SKDEV9.corporate_library_api.dto.CreateUserDTO;
import com.SKDEV9.corporate_library_api.model.Role;
import com.SKDEV9.corporate_library_api.model.User;
import com.SKDEV9.corporate_library_api.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository =  repository;
        this.encoder = encoder;
    }

    public User registerUSer(CreateUserDTO dto) {

        User newUser = new User();

        newUser.setEmail(dto.email());
        newUser.setNome(dto.nome());
        newUser.setMatricula(dto.matricula());


        String senhaCriptografada = encoder.encode(dto.senha());
        newUser.setSenha(senhaCriptografada);

        newUser.setRole(Role.ROLE_USER);

        return repository.save(newUser);


    }


}
