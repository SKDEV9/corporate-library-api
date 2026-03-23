package com.SKDEV9.corporate_library_api.controller;

import com.SKDEV9.corporate_library_api.dto.DadosAutenticacao;
import com.SKDEV9.corporate_library_api.infra.security.TokenService;
import com.SKDEV9.corporate_library_api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {


    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody DadosAutenticacao dados) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(tokenJWT);
    }
}