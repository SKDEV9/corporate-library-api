package com.SKDEV9.corporate_library_api.controller;

import com.SKDEV9.corporate_library_api.dto.CreateUserDTO;
import com.SKDEV9.corporate_library_api.model.User;
import com.SKDEV9.corporate_library_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<User> register(@RequestBody @Valid CreateUserDTO dto) {

        // criptografar e salvar
        User savedUser = service.registerUSer(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

    }


}
